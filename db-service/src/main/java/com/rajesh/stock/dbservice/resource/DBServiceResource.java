package com.rajesh.stock.dbservice.resource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.stock.dbservice.model.Company;
import com.rajesh.stock.dbservice.model.UserDetail;
import com.rajesh.stock.dbservice.model.Quotes;
import com.rajesh.stock.dbservice.model.ResponseData;
import com.rajesh.stock.dbservice.repo.UserRepository;
import com.rajesh.stock.dbservice.util.CompanyUtil;

@RestController
@RequestMapping("/rest/db")
@CrossOrigin(origins = "http://localhost:4200")
public class DBServiceResource {

    private UserRepository userRepository;
    
    @Autowired
    private CompanyUtil companyUtil;

    public DBServiceResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public List<UserDetail> getQuotes() {
        return userRepository.findAll();
    }
    
    @GetMapping("/company")
    public ResponseData<Company> getCompanyList() {
    	ResponseData<Company> data=new ResponseData<Company>();
        try {
        	data.setData(companyUtil.companyList());
			return data;
		} catch (IOException e) {
			data.setMessage("Error in fetching Company details");
			return new ResponseData<Company>();
		}
    }
    
    @GetMapping("/users")
    public ResponseData<UserDetail> getUserList() {
    	ResponseData<UserDetail> data=new ResponseData<UserDetail>();
        data.setData(userRepository.findAll());
		return data;
    }
    
    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username) {

        return getQuotesByUserName(username);
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes) {

        quotes.getQuotes()
                .stream()
                .map(quote -> new UserDetail(quotes.getUserName(), quote))
                .forEach(quote -> userRepository.save(quote));
        return getQuotesByUserName(quotes.getUserName());
    }


    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") final String username) {

        List<UserDetail> quotes = userRepository.findByUserName(username);
        quotes.stream().forEach(q -> userRepository.delete(q));

        return getQuotesByUserName(username);
    }


    private List<String> getQuotesByUserName(@PathVariable("username") String username) {
        return userRepository.findByUserName(username)
                .stream()
                .map(UserDetail::getQuote)
                .collect(Collectors.toList());
    }
}