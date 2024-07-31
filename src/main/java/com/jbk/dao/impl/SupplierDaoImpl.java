package com.jbk.dao.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.dao.SupplierDao;
import com.jbk.entity.SupplierEntity;
import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.SomethingWentWrongException;

@Component
public class SupplierDaoImpl implements SupplierDao {

	@Autowired 
	private SessionFactory sf;

	public SupplierDaoImpl() {
		
	}
	
	@Override
	public int addSupplier(SupplierEntity supplier) {
		
		 try {
		
		Session session = sf.openSession();
		session.save(supplier);
		session.beginTransaction().commit();
		
			return 1;
			
		} catch (PersistenceException e) { // ye exception tab hi aata hai jab data duplicate hota hai
			throw new ResourceAlreadyExistException("Supplier Already Exist");
			
		} catch (Exception e) {
			throw new SomethingWentWrongException("Something went wrong");
		}
		
//		
//		Session session = sf.openSession();
//		try {
//			SupplierEntity s1 = session.get(SupplierEntity.class, supplier.getSupplierId());
//			SupplierEntity s2 = session.get(SupplierEntity.class, supplier.getSupplierName());
//			SupplierEntity s3 = session.get(SupplierEntity.class, supplier.getMobile());
//			
//			System.out.println(s1 +" "+ s2 +" "+ s3);
//			
//			if(s1 == null && s2==null && s3==null) {
//				session.save(supplier);
//				session.beginTransaction().commit();
//				return 1;
//			}else {
//				throw new ResourceAlreadyExistException("alredy exist");
//		           }	
			
//		}catch(RollbackException e) {
//				throw new ResourceAlreadyExistException("Alredy exist one unique key");
				
			
//		}catch (Exception e) {
//			e.printStackTrace();
//			throw new SomethingWentWrongException("Something went wrong ");
//		}
//
//		
	}

	@Override
	public SupplierEntity getSupplier(long supplierId) {
		Session session = sf.openSession();
		SupplierEntity supplierEntity =null;
		try {
			
			supplierEntity = session.get(SupplierEntity.class, supplierId);
			return supplierEntity;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong ");
		}
		
	}
	
//	@Override
//	public SupplierEntity getSupplier(int supplierId) {
//		return null;

//		try {
//			for (Supplier Supplier : list) {
//
//				if ((Supplier.getSupplierId()) == SupplierId) {
//					return Supplier;
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new SomethingWentWrongException("Something went wrong while getting Supplier");
//		}
//
//		throw new ResourceNotExistException("Supplier not found");

//	}

//	3 - get all Suppliers

	@Override
	public List<SupplierEntity> getAllSuppliers() {
		return null;
//		return list;

	}

//	4 - delete Supplier by id

	@Override
	public int deleteSupplierById(long supplierId) {
		return 0;
//		try {
//			if (!list.isEmpty()) {
//				
//				boolean isDeleted = list.removeIf(Supplier -> Supplier.getSupplierId()==SupplierId);
//				if(isDeleted) {
//					return 1;
//				}
//			
////				for (Supplier Supplier : list) {
////					if (Supplier.getSupplierId() == SupplierId) {
////						list.remove(Supplier);
////						return 1;
////					}
////				}
				
//			}
//			else {
//				throw new ResourceNotExistException("List is empty ");
//			}
//		} catch (ResourceNotExistException e) {
//			throw new ResourceNotExistException("List is empty");
//		}
//
//		catch (Exception e) {
//			e.printStackTrace();
//			throw new SomethingWentWrongException("Something went wrong");
//		}
//
//		throw new ResourceNotExistException("Supplier Id not exist");
	}

	@Override
	public int updateSupplier(SupplierEntity supplier) {
		return 0;
//		try {
//			for (Supplier p : list) {
//				
//				if(p.getSupplierId() == Supplier.getSupplierId()) {
//					
//					p.setSupplierName(Supplier.getSupplierName());
//					p.setSupplierQty(Supplier.getSupplierQty());
//					p.setSupplierPrice(Supplier.getSupplierPrice());
//					p.setMfgDate(Supplier.getMfgDate());
//					p.setExpDate(Supplier.getExpDate());
//						
////					list.set(list.indexOf(p), Supplier);        //quick 1 step code need to understand very well
//					return 1;
//				}
//			}
//			
//		} catch (Exception e) {
//			throw new SomethingWentWrongException("Something went wrong exception");
//		}
//         
//		 throw new ResourceNotExistException("Supplier not Exist to update");
	}


	@Override
	public List<SupplierEntity> supplierGreaterThanGivenPrice(double supplierPrice) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<SupplierEntity> supplierOrderByPrice() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<SupplierEntity> supplierByName(String supplierName) {
		// TODO Auto-generated method stub
		return null;
	}





	

//	@Override
//	public List<SupplierEntity> SupplierGreaterThanGivenPrice(double supplierPrice) {
//		return null;
//		ArrayList<Supplier> newlist1=new ArrayList<>();
//		try {
//			List<Supplier> newlist = list.stream()
//	                               .filter(Supplier -> Supplier.getSupplierPrice() > SupplierPrice)
//	                               .collect(Collectors.toList());
//			       
//			if(!newlist.isEmpty()) {
//				return newlist;
//			}
//		} catch (Exception e) {
//			throw new SomethingWentWrongException("Something went wrong");
//		}
//		throw new ResourceNotExistException("Supplier not found");
//	}

//	@Override
//	public List<SupplierEntity> supplierOrderByPrice() {
//		return null;
//	try {
//		  List<Supplier> sortedlist = list.stream()
//                                     .sorted(Comparator.comparingDouble(Supplier::getSupplierPrice))
//                                     .collect(Collectors.toList());
//		 
//			if(!sortedlist.isEmpty()) {
//				return sortedlist;
//			}
//		} catch (Exception e) {
//			throw new SomethingWentWrongException("Something went wrong");
//		}
//		throw new ResourceNotExistException("Supplier not found : list is empty");
//	}

//	@Override
//	public List<SupplierEntity> supplierByName(String supplierName) {
//		return null;
//		try {
//			
//			List<Supplier> newlist = list.stream()
//			        .filter(Supplier -> Supplier.getSupplierName().toLowerCase().contains(SupplierName) || Supplier.getSupplierName().toLowerCase().matches(SupplierName+"[^\\w]*"))
//			        .collect(Collectors.toList());
//			
//			if(!newlist.isEmpty()) {
//				return newlist;
//			}
//		} catch (Exception e) {
//			throw new SomethingWentWrongException("Something went wrong");
//		}
//		throw new ResourceNotExistException("Supplier not found : list is empty");
//	}

}
