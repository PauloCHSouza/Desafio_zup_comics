package com.paulo_comics.comics.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

	@Autowired
	private UserRepository rep;
	
	public List<User> getUsers(){
		
		List<User> u = rep.findAll();

		if (u.isEmpty() || u == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum registro encontrado.");
		} else {
			return u;
		}
	}

	public Optional<User> getUsersById(Long id) {
		Optional<User> u = rep.findById(id);
		
		if (u.isEmpty() || u == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum registro encontrado com o ID: " + id);
		} else {
			return u;
		}
	}
	
	public Optional<User> getUsersByCpf(String cpf) {
		Optional<User> u = rep.findByCpf(cpf);
		return u;
	}
	
	public Optional<User> getUsersByEmail(String email) {
		Optional<User> u = rep.findByEmail(email);
		return u;
	}

	public User save(User user) {	
		
		if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foram informados dados do Usuário");
		
		if (user.getNome().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome não informado!");
		
		if (user.getEmail().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail não informado!");
		
		if (user.getCpf().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF não informado!");
		
		if (user.getDtNascimento() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de nascimento não informada ou inválida!");
		
		if (!getUsersByEmail(user.getEmail()).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail Informado Já existe cadastrado no sistema!");
		
		if (!getUsersByCpf(user.getCpf()).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF Informado Já existe cadastrado no sistema!");
		
		rep.save(user);
		
		throw new ResponseStatusException(HttpStatus.CREATED, "Usuário cadastrado com sucesso!");
	}

	public User update(User user, Long id){
        Assert.notNull(id, "Não foi possível atualizar o registro");

        Optional<User> optional = getUsersById(id);
        if (optional.isPresent()){
    		
    		if (user.getNome().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome não informado!");
    		
    		if (user.getEmail().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail não informado!");
    		
    		if (user.getCpf().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF não informado!");
    		
    		if (user.getDtNascimento() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de nascimento não informada ou inválida!");
    		
            User db = optional.get();
            db.setNome(user.getNome());
            db.setEmail(user.getEmail());
            db.setCpf(user.getCpf());
            db.setDtNascimento(user.getDtNascimento());

            rep.save(db);

			throw new ResponseStatusException(HttpStatus.OK, "Usuário atualizado com sucesso!");
        }else{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel localizar o registro de ID: " + id);
        }

    }

	public User delete(Long id) {
		Optional<User> optional = getUsersById(id);
		if (optional.isPresent()) {
			rep.deleteById(id);
			throw new ResponseStatusException(HttpStatus.OK, "Usuário excluído com sucesso!");
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel localizar o registro de ID: " + id);
		}
	}
	
}
