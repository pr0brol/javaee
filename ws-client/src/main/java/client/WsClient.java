package client;

import ru.geekbrains.repository.ProductRepositoryWs;
import ru.geekbrains.repository.ProductService;

import java.net.MalformedURLException;
import java.net.URL;

public class WsClient {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://127.0.0.1:8080/base-webapp/ProductService/ProductRepositoryImpl?wsdl");
        ProductService productService = new ProductService(url);
        ProductRepositoryWs productRepositoryImplPort = productService.getProductRepositoryImplPort();

        productRepositoryImplPort.findAll().forEach(p -> System.out.println(p.getName()));

    }
}

