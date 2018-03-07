package com.packt.webstore.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.packt.webstore.domain.Product;
import com.packt.webstore.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts() );
		return "products";
	}
	
	@RequestMapping("/all")
	public String allProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	
/*	@RequestMapping("/all") // Druga, rzadko uzywana metoda aktualizowania modelu i zwracania modelu przez kontroler
	public ModelAndView allProducts() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("products", productService.getAllProducts());
		modelAndView.setViewName("products");
		return modelAndView;
	} */
	
	@RequestMapping("/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		return "products";
	}
	
	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(@MatrixVariable(pathVar="ByCriteria")
	Map<String, List<String>> filterParams, Model model) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}
	
	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}
	
	@RequestMapping("/{category}/{price}")
	public String filterProducts(@PathVariable("category") String productCategory,
			@MatrixVariable(pathVar="price") Map<String, List<String>> filterParams,
			@RequestParam("manufacturer") String manufacturer, 
			 Model model) {
		model.addAttribute("products", productService.getProductsByFilters(productCategory, filterParams,
				manufacturer));
		
		return "products";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, 
			BindingResult result, HttpServletRequest request) {
		
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Proba wiazania niedozwolonych pól: " + 
			StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		
		MultipartFile productImage = newProduct.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		if (productImage!=null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(rootDirectory+"resources\\images\\" +
			newProduct.getProductId()+ ".png"));
			} catch (Exception e) {
				throw new RuntimeException("Niepowodzenia podczas proby zapisu obrazka produktu", e);
			}
		}
		
		MultipartFile productPdf = newProduct.getProductPdf();
		if (productPdf!=null && !productPdf.isEmpty()) {
			try {
				productPdf.transferTo(new File(rootDirectory+"resources\\pdf\\" +
			newProduct.getProductId()+".pdf"));
			} catch (Exception e) {
				throw new RuntimeException("Niepowodzenie podczas proby zapisu pliku pdf produktu",e);
			}
		}
		
		productService.addProduct(newProduct);
		return "redirect:/products";
	}
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setDisallowedFields("unitsInOrder", "discontinued");
	}
}
