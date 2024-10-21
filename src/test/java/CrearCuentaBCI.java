package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class CrearCuentaBCI {
    public static void main(String[] args) throws InterruptedException {
        String rutaProyecto = System.getProperty("user.dir");
        String rutaDriver = rutaProyecto +
                "\\src\\test\\resources\\drivers\\chromedriver.exe";
        //Enlazar el webDriver al browser de nuestro sistema
        System.setProperty("webdriver.chrome.driver", rutaDriver);

        //Instanciar un objeto que represente al browser (driver)
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.bci.cl/personas");
        driver.manage().window().maximize();
        //espere 5 segundos
        Thread.sleep(5000);


        //clic al boton Hazte cliente
        WebElement btnNuevoCliente;
        By byBtnNuevoCliente = By.xpath("//a[contains(text(),'Hazte Cliente')]");
        btnNuevoCliente = driver.findElement(byBtnNuevoCliente);
        btnNuevoCliente.click();

        Thread.sleep(10000);

        //ingreso rut
        // Cambia al iframe que contiene el campo de RUT
        driver.switchTo().frame("myFrame");

        // Localiza el campo de RUT
        WebElement rutField = driver.findElement(By.xpath("//input[@placeholder='Ingresa tu RUT']"));

        // Ingresa el RUT
        rutField.sendKeys("5.204.595-9");
        Thread.sleep(3000);
        rutField.sendKeys(Keys.TAB);

        //ingreso numero de documento

       // driver.switchTo().frame("frame");

        WebElement NumSerie = driver.findElement(By.xpath("//input[contains(@placeholder,'Ingresa n')]"));
        NumSerie.sendKeys("847.939.376");





        //WebElement inputRut = driver.findElement(By.xpath("//input[@placeholder='Ingresa tu RUT']"));
        //inputRut.sendKeys("5.204.695-9");  // Aquí ingresas el RUT que desees




        //driver.findElement(By.id("_ngcontent-mgm-c38")).sendKeys("5.205.595-9");

       //WebElement inputRut = driver.findElement(By.xpath("//input[contains(@placeholder,'Ingresa tu Rut')]);
       // WebElement labelRut = driver.findElement(By.xpath('//label[@_ngcontent-mgm-c38]'"//text"));

          //      WebElement labelMarketing = driver.findElement(By.xpath("//label[@for='checkbox-marketing']"));
        //labelRut.sendKeys("5.205.595-9");



        Thread.sleep(2000);
/*

        //clic en boton siguiente
        WebElement btnSiguiente1;
        By byBtnSiguiente1 = By.xpath("//button[@data-testid=\"submit\"]");
        btnSiguiente1 = driver.findElement(byBtnSiguiente1);
        btnSiguiente1.click();

        Thread.sleep(2000);

        // paso 1 de 3
        // crear contraseña
        driver.findElement(By.id("new-password")).sendKeys("ksie929a7u");

        Thread.sleep(2000);

        // clic en botón siguiente
        WebElement btnSiguiente2;
        By byBtnSiguiente2 = By.xpath("//button[@data-testid=\"submit\"]");
        btnSiguiente2 = driver.findElement(byBtnSiguiente2);
        btnSiguiente2.click();
        Thread.sleep(2000);

        //paso2 de 3
        //ingreso nombre
        driver.findElement(By.id("formcontrolname")).sendKeys("5.204.595-9");
        Thread.sleep(2000);

 /*       //ingreso fecha de nacimiento
        driver.findElement(By.id("day")).sendKeys("22");
        Thread.sleep(2000);

        driver.findElement(By.id("month")).sendKeys("Abril");
        Thread.sleep(2000);

        driver.findElement(By.id("year")).sendKeys("2000");
        Thread.sleep(2000);

        //ingreso genero
        WebElement labelGenero = driver.findElement(By.xpath("//label[@for='gender_option_female']"));
        labelGenero.click();
        Thread.sleep(2000);

        // clic en botón siguiente
        WebElement btnSiguiente3;
        By byBtnSiguiente3 = By.xpath("//button[@data-testid=\"submit\"]");
        btnSiguiente3 = driver.findElement(byBtnSiguiente3);
        btnSiguiente3.click();
        Thread.sleep(2000);

        //Paso 3 de 3
        //ingreso opcion no quiero recibir
        WebElement labelMarketing = driver.findElement(By.xpath("//label[@for='checkbox-marketing']"));
        labelMarketing.click();
        Thread.sleep(2000);

        // clic en botón Registrarse
        WebElement btnSiguiente4;
        By byBtnSiguiente4 = By.xpath("//button[@data-testid=\"submit\"]");
        btnSiguiente4 = driver.findElement(byBtnSiguiente4);
        btnSiguiente4.click();
        Thread.sleep(2000);

        //tiempo para ingresar la captcha a mano

        JOptionPane.showMessageDialog(null, "Por favor, completa el CAPTCHA, el sistema solo va a continuar.");
        Thread.sleep(20000);

        //continuación

        WebElement btnSiguiente5;
        By byBtnSiguiente5 = By.xpath("//button[@data-is-ready=\"true\"]");
        btnSiguiente5 = driver.findElement(byBtnSiguiente5);
        btnSiguiente5.click();
        Thread.sleep(30000);

        driver.close();
*/
    }

}


