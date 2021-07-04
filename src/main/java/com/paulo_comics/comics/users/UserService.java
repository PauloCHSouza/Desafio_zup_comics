package com.paulo_comics.comics.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserService {

	@Autowired
	private UserRepository rep;
	
	public Iterable<User> getUsers(){
		return rep.findAll();
	}

	public Optional<User> getUsersById(Long id) {
		return rep.findById(id);
	}

	public Iterable<User> getUsersByNome(String nome) {
		return rep.findByNome(nome);
	}

	public Iterable<User> getUsersByCpf(String cpf) {
		// TODO Auto-generated method stub
		return rep.findByCpf(cpf);
	}

	public User save(User user) {		
		return rep.save(user);
	}

	public User update(User user, Long id){
        Assert.notNull(id, "Não foi possível atualizar o registro");

        Optional<User> optional = getUsersById(id);
        if (optional.isPresent()){
            User db = optional.get();
            db.setNome(user.getNome());
            db.setEmail(user.getEmail());
            db.setCpf(user.getCpf());
            db.setDtNascimento(user.getDtNascimento());

            rep.save(db);
            return db;
        }else{
            throw new RuntimeException("Não foi possível atualizar o registro");
        }

    }

	public void delete(Long id) {
		Optional<User> optional = getUsersById(id);
		if(optional.isPresent()) {
			rep.deleteById(id);
		}else {
			throw new RuntimeException("Não foi possivel localizar o registro");
		}
	}

}
