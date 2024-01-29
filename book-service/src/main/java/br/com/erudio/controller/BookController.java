package br.com.erudio.controller;

import br.com.erudio.model.Book;
import br.com.erudio.proxy.CambioProxy;
import br.com.erudio.repository.BookRepository;
import br.com.erudio.response.Cambio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy;


    @GetMapping(value = "{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {

        var book = repository.getReferenceById(id);
        if(book == null) {
            throw new RuntimeException("Book not found!");
        }

        var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

        var port = environment.getProperty("local.server.port");

        book.setEnvironment("Book Server port: " + port + " Cambio Port " + cambio.getEnvironment());
        book.setPrice(cambio.getConversionValue());

        return book;
    }


    /**
     * //http://localhost:8100/book-service/14/BRL
    @GetMapping(value = "{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {

        var book = repository.getReferenceById(id);
        if(book == null) {
            throw new RuntimeException("Book not found!");
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);

        var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}",
                Cambio.class, params);

        var cambio = response.getBody();

        var port = environment.getProperty("local.server.port");

        book.setEnvironment(port);
        book.setPrice(cambio.getConversionValue());

        return book;
    }
     */

}
