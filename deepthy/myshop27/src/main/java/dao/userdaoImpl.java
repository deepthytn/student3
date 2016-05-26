package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.MyProduct;
import model.UserRegisteration;
@Transactional
@Repository
public class userdaoImpl implements userdaoIn {
	List<MyProduct> prods;
	
	 @Autowired
SessionFactory sessionFactory;
	public UserRegisteration getRowById(int id){
		 Session s=sessionFactory.getCurrentSession();
		 UserRegisteration p=(UserRegisteration)s.load(UserRegisteration.class, id);
		  
		   return p;
	}
	
	
}
