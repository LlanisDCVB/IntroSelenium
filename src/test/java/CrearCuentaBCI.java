package scripts;

import org.openqa.selenium.*;

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
        Thread.sleep(2000);


        //clic al boton Hazte cliente
        WebElement btnNuevoCliente;
        By byBtnNuevoCliente = By.xpath("//a[contains(text(),'Hazte Cliente')]");
        btnNuevoCliente = driver.findElement(byBtnNuevoCliente);
        btnNuevoCliente.click();

        Thread.sleep(5000);

        //ingreso rut
        // Cambia al iframe que contiene el campo de RUT
        driver.switchTo().frame("myFrame");

        // Localiza el campo de RUT
        WebElement rutField = driver.findElement(By.xpath("//input[@placeholder='Ingresa tu RUT']"));

        // Ingresa el RUT
        rutField.sendKeys("5.204.595-9");
        Thread.sleep(1000);
        rutField.sendKeys(Keys.TAB);

        //ingreso numero de documento

        WebElement NumSerie = driver.findElement(By.xpath("//input[contains(@placeholder,'Ingresa n')]"));
        NumSerie.sendKeys("847.939.376");
        Thread.sleep(1000);
        //ingreso telefono
        WebElement NumTel = driver.findElement(By.xpath("//input[contains(@placeholder,'Ingresa tu tel')]"));
        NumTel.sendKeys("74834503");
        Thread.sleep(1000);

        //ingreso correo
        WebElement email = driver.findElement(By.xpath("//input[contains(@placeholder,'Ingresa tu ema')]"));
        email.sendKeys("cuentadecorreo@tucuenta.cl");
        Thread.sleep(1000);

        //ingreso confirma correo
        WebElement REemail = driver.findElement(By.xpath("//input[@placeholder='Vuelve a ingresar tu email']"));
        REemail.sendKeys("cuentadecorreo@tucuenta.cl");
        Thread.sleep(1000);
        REemail.sendKeys(Keys.TAB);


        //mover scroll

        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll hacia abajo 500 píxeles
        js.executeScript("window.scrollBy(0, 500);");
        Thread.sleep(2000);
        // chek en politicas de privacidad

        driver.findElement(By.xpath("//*[@id=\"contenedorApp\"]/div/div/app-datos-basicos/form/div/div[2]/bci-wk-checkbox")).click();
        Thread.sleep(2000);


        //clic en boton continuar

        WebElement botonContinuar = driver.findElement(By.xpath("//button[text()='Continuar']"));
        botonContinuar.click();


        Thread.sleep(2000);




/*
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


