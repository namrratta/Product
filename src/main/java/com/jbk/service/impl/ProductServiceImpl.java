package com.jbk.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.dao.impl.ProductDaoImpl;
import com.jbk.entity.CategoryEntity;
import com.jbk.entity.ProductEntity;
import com.jbk.exception.ResourceNotExistException;
import com.jbk.exception.SomethingWentWrongException;
import com.jbk.model.CategoryModel;
import com.jbk.model.ProductModel;
import com.jbk.model.SupplierModel;
import com.jbk.dao.ProductDao;
import com.jbk.service.ProductService;
import com.jbk.validation.ObjectValidation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;

@Component
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao dao = new ProductDaoImpl(); // yaha pe pura new likha to bhi chalega
	
	@Autowired
	private ObjectValidation validation;
	Map<String, String> errorMap= new HashMap<>();     //globally declaring errormap;
	Map<Integer ,Map<String, String>> rowErrorMap=new HashMap<>(); 
	Map<String, Map<Integer, Map<String, String>>> badRows = new HashMap<>();
	LinkedHashMap<String,Object> finalMap = new LinkedHashMap<>();
	List<Integer> existedRowNum=new ArrayList<>();

	@Autowired
	private ModelMapper mapper;
	
	int totalSheetRecord=0;
	int alreadyExistedRecord=0;

//	 1: Add product code 

	@Override
	public int addProduct(ProductModel product) {

		String productId = new SimpleDateFormat("YYYYMMDDhhmmss").format(new java.util.Date());
		product.setProductId(Long.parseLong(productId));

		System.out.println(productId);

		ProductEntity productEntity = mapper.map(product, ProductEntity.class);

		return dao.addProduct(productEntity);

	}

//  2:  Get product by id 

	@Override
	public ProductModel getProductById(long productId) {

		ProductEntity productEntity = dao.getProductById(productId);

		if (productEntity != null) {
			ProductModel productModel = mapper.map(productEntity, ProductModel.class);

			return productModel;

		} else {
			throw new ResourceNotExistException("Product not exist with id " + productId);
		}
	}

// 3 : Delete product by id 

	@Override
	public int deleteProductById(long productId) {

		return dao.deleteProductById(productId);
	}

// 4: Update product by Id
	@Override
	public int updateProduct(ProductModel product) {

		ProductEntity productEntity = mapper.map(product, ProductEntity.class);

		return dao.updateProduct(productEntity);
	}

//****************************************** Criteria ************************************************************
//	5 - get all products	
	@Override
	public List<ProductModel> getAllProducts() {
		List<ProductEntity> list = dao.getAllProducts();
		List<ProductModel> al = new ArrayList<ProductModel>();

		if (!list.isEmpty()) {
			for (ProductEntity productEntity : list) {
				ProductModel productModel = mapper.map(productEntity, ProductModel.class);
				al.add(productModel);
			}
			return al;
		} else {

			throw new ResourceNotExistException("There are no products");
		}

	}

// 6 : display all products whose Products price >= 50 rupees (given price)

	@Override
	public List<ProductModel> productGreaterThanGivenPrice(double productPrice) {

		List<ProductEntity> list = dao.productGreaterThanGivenPrice(productPrice);
		List<ProductModel> al = new ArrayList<ProductModel>();
		if (!list.isEmpty()) {
			for (ProductEntity productEntity : list) {
				ProductModel productModel = mapper.map(productEntity, ProductModel.class);
				al.add(productModel);

			}
			return al;
		} else {
			throw new ResourceNotExistException("Products not exist greater than given :" + productPrice);
		}

	}

// 7 : display all products by their ascending order of price 
	@Override
	public List<ProductEntity> productOrderByPrice() {

		return dao.productOrderByPrice();
	}

// 8 : Display products having name mathing given name and other results containing given name	

	@Override
	public ProductModel productByName(String productName) {

		ProductEntity productEntity = dao.productByName(productName);
		if (productEntity != null) {
			ProductModel productModel = mapper.map(productEntity, ProductModel.class);
			return productModel;
		} else {
			throw new ResourceNotExistException("Products not found with name :" + productName);
		}

	}

//  : get max price product

	@Override
	public List<ProductModel> maxPriceProduct() {
		List<ProductEntity> list = dao.maxPriceProduct();
		List<ProductModel> al = new ArrayList<ProductModel>();
		if (!list.isEmpty()) {
			for (ProductEntity productEntity : list) {
				ProductModel productModel = mapper.map(productEntity, ProductModel.class);
				al.add(productModel);

			}
			return al;
		} else {
			throw new ResourceNotExistException("Products not exist");
		}

	}

//	: product price is between 500 and 1000

	// select * from product where product_price is between 500 and 1000;
	@Override
	public List<ProductModel> betweenPriceProduct(double minPrice, double maxPrice) {

		List<ProductEntity> list = dao.betweenPriceProduct(minPrice, maxPrice);
		List<ProductModel> al = new ArrayList<ProductModel>();
		if (!list.isEmpty()) {
			for (ProductEntity productEntity : list) {

				ProductModel productModel = mapper.map(productEntity, ProductModel.class);
				al.add(productModel);

			}
			return al;
		} else {
			throw new ResourceNotExistException("Products not exist to compare");
		}

	}
//	: count products 
	
	
	@Override
	public int countProducts() {
		
		return dao.countProducts();
	}

//****************************************** multipart file ************************************************************
// 19: Receiving file from postman

	@Override
	public LinkedHashMap<String, Object> uploadFile(MultipartFile file) {
		String msg = null;
		int uploadCount=0;
		int exist=0;
       List< ProductEntity> list=new  ArrayList<>(); 
       
		String fileName = file.getOriginalFilename(); // file se original filename nikalna

		try {
			// apne project me file lane ka process - byte by byte read - location pe filename se file banani - data write karna
			
			byte[] DataInBytes = file.getBytes(); // file se byte by byte data nikalna
			FileOutputStream fs = new FileOutputStream("src/main/resources" + File.separator + fileName); // file ka location dena aur naam dena jaha banani h aur jis naam se file bana hai
			fs.write(DataInBytes);                                                                       // file me data write karne ki method

			// apne location pe excel aagayi abhi -- read excel data ki method call ki aur productModelList get ki
			
			List<ProductModel> productModelList = readExcel("src/main/resources" + File.separator + fileName);
			
			for (ProductModel productModel : productModelList) {
				
				ProductEntity productEntity = mapper.map(productModel, ProductEntity.class);
				
				int status = dao.addProduct(productEntity);
				uploadCount += status;
			}
//				msg="uploaded : "+ uploadCount + " Already Exist count : "+(totalSheetRecord - uploadCount);
				
				finalMap.put("Total record in sheet", totalSheetRecord);
				finalMap.put("Uploaded record in database", uploadCount);
				finalMap.put("Total existed record in database", alreadyExistedRecord);
				finalMap.put("alredy existed row number",existedRowNum);
				finalMap.put("Total excluded", rowErrorMap.size());
				finalMap.put("Bad record row number", rowErrorMap);
				
				//list.add(productEntity);
			
			
//			 String msgdao = dao.uploadFile(file);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("something went wrong");
		}
		return finalMap;
	}
	

// method to read file and print on console
//	public List<ProductModel> readExcelOnly(String fileLocation) {
//
//		try {
//			SimpleDateFormat inputFormat = new SimpleDateFormat("*d/*m/yyyy");
//			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//			// FileInputStream fs = new FileInputStream(fileLocation); //to provide path-isme path denge
//			// Workbook workbook = new XSSFWorkbook(fs); //ye constructor file input stream leta hai jisme path hai
//
//			Workbook workbook = new XSSFWorkbook(fileLocation); // ye constructor direct path leta hai to upar wale ki jarurat nai
//																             
//			Sheet sheet = workbook.getSheetAt(1); // workbbok k ander ka sheet mila
//			Iterator<Row> rows = sheet.iterator(); // sheet me se rows milenge
//
//			while (rows.hasNext()) { // rows ko iterate karenge
//				Row row = (Row) rows.next();
//
//				Iterator<Cell> cells = row.cellIterator(); // cells milenge yahape
//
//				while (cells.hasNext()) { // cell ko iterate
//					Cell cell = (Cell) cells.next();
//
//					CellType cellType = cell.getCellType();
//					if (cellType == CellType.STRING) {
//						System.out.print(cell.getStringCellValue() + "\t");
//
//					} else if (cellType == CellType.NUMERIC) {
//						if (DateUtil.isCellDateFormatted(cell)) {
//							Date date = cell.getDateCellValue();
//							String formattedDate = outputFormat.format(date);
//							System.out.print(formattedDate + "\t");
//						} else {
//							System.out.print(cell.getNumericCellValue() + "\t");
//						}
//					} else {
//						System.out.print("other" + "\t");
//					}
//
//				}
//				System.out.println();
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new SomethingWentWrongException("something went wrong");
//		}
//		return null;
//	}

//	method to read file and list me add karega productModel k andar

	public List<ProductModel> readExcel(String fileLocation) {
		
		List<ProductModel> productModelList = new ArrayList();
		try {
			FileInputStream fs = new FileInputStream(fileLocation);
			Workbook workbook = WorkbookFactory.create(fs); // it will create according type of file extension xlsx and
															// xls

			//Workbook workbook = new XSSFWorkbook(fileLocation);      //for xssf type 
			Sheet sheet = workbook.getSheetAt(1);
			totalSheetRecord = sheet.getLastRowNum();
			Iterator<Row> rows = sheet.rowIterator(); //get rows

			// row iteration
			while (rows.hasNext()) {
				Row row = (Row) rows.next();

				if (row.getRowNum() == 0) {
					continue;
				}
				
				//create productModel instance-jisme abhi sari values set karni hai
				ProductModel productModel = new ProductModel();
				
				// getting unique productId
				String productId = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
				int rowNum=row.getRowNum();
                productModel.setProductId(Long.parseLong(productId+rowNum));
				

				// getting cells in 1 row
				Iterator<Cell> cells = row.cellIterator();

				// cell iteration
				while (cells.hasNext()) {
					Cell cell = (Cell) cells.next();

					int columnIndex = cell.getColumnIndex();

					switch (columnIndex) {
					case 0: {
						productModel.setProductName(cell.getStringCellValue());
						//System.out.print(cell.getStringCellValue() + "\t");
						break;
					}
					case 1: {
						SupplierModel  supplierModel =new SupplierModel();
						supplierModel.setSupplierId((int)cell.getNumericCellValue());
						productModel.setSupplier(supplierModel);
						//System.out.print(cell.getNumericCellValue() + "\t");
						break;
					}
					case 2: {
						CategoryModel categoryModel=new CategoryModel();
						categoryModel.setCategoryId((int)cell.getNumericCellValue());
						productModel.setCategory(categoryModel);
						//System.out.print(cell.getNumericCellValue() + "\t");
						break;
					}
					case 3: {
						productModel.setProductQty((int)cell.getNumericCellValue());
						//System.out.print(cell.getNumericCellValue() + "\t");
						break;
					}
					case 4: {
						productModel.setProductPrice((int)cell.getNumericCellValue());
						//System.out.print(cell.getNumericCellValue() + "\t");
						break;
					}
					case 5: {
						SimpleDateFormat inputFormat = new SimpleDateFormat("*d/*m/yyyy");
						SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date date = cell.getDateCellValue();
						String formattedDate = outputFormat.format(date);
						
						productModel.setMfgDate(formattedDate);
						//System.out.print(formattedDate + "\t");
						break;
					}
					case 6: {
						SimpleDateFormat inputFormat = new SimpleDateFormat("*d/*m/yyyy");
						SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date date = cell.getDateCellValue();
						String formattedDate = outputFormat.format(date);
						
						productModel.setExpDate(formattedDate);
						//System.out.print(formattedDate + "\t");
						break;
					}
					case 7: {
						productModel.setDeliveryCharge((int)cell.getNumericCellValue());
						//System.out.println(cell.getNumericCellValue() + "\t");
						break;
					}
				
					}

				}
				
				errorMap = validation.validateProduct(productModel);
				if(!errorMap.isEmpty()) {
					//error
					rowErrorMap.put(row.getRowNum(), errorMap);
					
				}else {
					//ok
					try {
						
						ProductModel dbProduct = productByName(productModel.getProductName());
						if(dbProduct != null) {
							alreadyExistedRecord +=1;
							existedRowNum.add(row.getRowNum());
						}
					} catch (ResourceNotExistException e) {
						productModelList.add(productModel);
					}
				}
				
				
				

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("something went wrong");
		}

		return productModelList;

	}


}
