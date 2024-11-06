import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CatalogoLibros {
    public static void main(String[] args) throws InterruptedException{

        String rutaProyecto = System.getProperty("user.dir");
        String rutaDriver = rutaProyecto+
                "\\src\\test\\resources\\drivers\\chromedriver.exe";

        //Enlazar el webDriver al browser de nuestro sistema
        System.setProperty("webdriver.chrome.driver",rutaDriver);

        //Instanciar un objeto que represente al browser (driver)
        WebDriver driver = new ChromeDriver();

        driver.get("https://catalogotextos.mineduc.cl/catalogo-textos/login/login?tipo=ee");

        driver.manage().window().maximize();

        //espere 5 segundos
        Thread.sleep(5000);

        //clic al boton Estudiante
        WebElement btnCambiaEstudiante;
       // By byBtnCambiaEstudiante = By.xpath("//button[@data-testid=\"login-button\"]");
        By byBtnCambiaEstudiante = By.xpath("//a[@data-tipo-usuario='alumno']");

        btnCambiaEstudiante = driver.findElement(byBtnCambiaEstudiante);
        btnCambiaEstudiante.click();

        Thread.sleep(5000);

        //coloco rut del estudiante
        driver.findElement(By.id("run_alumno")).sendKeys("24304457-K");
        Thread.sleep(3000);
        driver.findElement(By.id("nivel_alumno")).sendKeys("5° Básico");
        Thread.sleep(3000);

//mover scroll

        JavascriptExecutor Scroll = (JavascriptExecutor) driver;
        // Scroll hacia abajo 500 píxeles
        Scroll.executeScript("window.scrollBy(0, 500);");
        Thread.sleep(2000);

        // Usar JavaScript para establecer el valor de la fecha directamente
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('fecha_nacimiento_alumno').value='14-06-2013';");

        Thread.sleep(2000);

        //clic al boton Ingresar
        WebElement btnIngresar;
        By byBtnIngresar = By.xpath("//button[@id=\"ingresar\"]");

        btnIngresar = driver.findElement(byBtnIngresar);
        btnIngresar.click();


        Thread.sleep(5000);

        driver.close();
    }
}
