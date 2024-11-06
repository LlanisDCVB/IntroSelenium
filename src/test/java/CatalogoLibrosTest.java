import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class CatalogoLibrosTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        String rutaProyecto = System.getProperty("user.dir");
        String rutaDriver = rutaProyecto + "\\src\\test\\resources\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", rutaDriver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://catalogotextos.mineduc.cl/catalogo-textos/login/login?tipo=ee");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void CP001_CambiarAEstudiante() throws InterruptedException {
        Thread.sleep(5000); // Espera para asegurar la carga de la página

        // Hacer clic en el botón de "Estudiante"
        WebElement btnCambiaEstudiante = driver.findElement(By.xpath("//a[@data-tipo-usuario='alumno']"));
        btnCambiaEstudiante.click();
        Thread.sleep(5000);
    }

    @Test
    public void CP002_IngresarDatosEstudianteInvalido() throws InterruptedException {
        CP001_CambiarAEstudiante(); // Asegurarse de que estamos en el modo estudiante

        // Generar un RUT incorrecto y validar el mensaje de error
        String rutIncorrecto = generarRUT();
        ingresarDatosEstudiante(rutIncorrecto, "5° Básico", "14-06-2013");

        // Verificar mensajes de error para el RUT incorrecto
        validarMensajesDeError();
    }

    @Test
    public void CP003_IngresarAlCatalogo() throws InterruptedException {
        // Limpiar el campo y colocar el RUT correcto
        driver.findElement(By.id("run_alumno")).clear();
        ingresarDatosEstudiante("24304457-K", "5° Básico", "14-06-2013");

        // Hacer clic en el botón "Ingresar"
        WebElement btnIngresar = driver.findElement(By.xpath("//button[@id=\"ingresar\"]"));
        btnIngresar.click();
        Thread.sleep(2000);
    }

    @Test
    public void CP004_SeleccionarYDescargarLibro() throws InterruptedException {
        CP003_IngresarAlCatalogo(); // Asegurarse de que el estudiante ha ingresado al catálogo

        // Seleccionar el libro de lenguaje para ver su contenido
        WebElement enlaceLyC = driver.findElement(By.xpath("//a[@id=\"itemSeleccionable_0\"]"));
        enlaceLyC.click();
        Thread.sleep(10000);

        // Descargar el libro
        WebElement descargarLyC = driver.findElement(By.xpath("//a[@class='btn btn-default' and contains(., 'Descargar')]"));
        descargarLyC.click();
        Thread.sleep(10000);
    }

    @Test
    public void CP005_VerificarArchivoDeDescarga() {
        try {
            CP004_SeleccionarYDescargarLibro(); // Asegurarse de que el libro se intenta descargar
        } catch (InterruptedException e) {
            System.out.println("La operación fue interrumpida: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restablecer el estado de interrupción del hilo
        }

        // Verificar si el enlace de descarga está presente
        try {
            WebElement descargarLyC = driver.findElement(By.xpath("//a[@class='btn btn-default' and contains(., 'Descargar')]"));
            Assertions.assertTrue(descargarLyC.isDisplayed(), "El enlace de descarga está presente.");
            System.out.println("El archivo de descarga está disponible.");
        } catch (NoSuchElementException e) {
            System.out.println("El archivo de descarga no se encuentra disponible.");
        }
    }

    // Método para ingresar datos del estudiante (RUT, nivel, y fecha)
    private void ingresarDatosEstudiante(String rut, String nivel, String fechaNacimiento) throws InterruptedException {
        driver.findElement(By.id("run_alumno")).sendKeys(rut);
        Thread.sleep(3000);

        driver.findElement(By.id("nivel_alumno")).sendKeys(nivel);
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('fecha_nacimiento_alumno').value='" + fechaNacimiento + "';");
        Thread.sleep(2000);
    }

    // Método para validar mensajes de error cuando el RUT es incorrecto
    private void validarMensajesDeError() throws InterruptedException {
        // Hacer clic en el botón "Ingresar" para verificar el error
        WebElement btnIngresar = driver.findElement(By.xpath("//button[@id='ingresar']"));
        btnIngresar.click();
        Thread.sleep(2000);

        // Verificar el mensaje de error del RUT
        WebElement errorMessage = driver.findElement(By.id("login_error_alumno_run"));
        Assertions.assertTrue(errorMessage.getText().startsWith("RUN del estudiante no es v"), "Error en el mensaje de RUT inválido");
        System.out.println("El rut ingresado no es el correcto");

        // Verificar mensaje de advertencia de estudiante incorrecto
        WebElement warningMessage = driver.findElement(By.id("warning"));
        Assertions.assertTrue(warningMessage.getText().startsWith("nuestros registros, sus datos no son"), "Error en el mensaje de advertencia");
        System.out.println("Estudiante erróneo");
    }

    // Método para generar un RUT incorrecto
    public static String generarRUT() {
        Random random = new Random();
        int numeroBase = 1000000 + random.nextInt(9000000);
        int digitoVerificador = calcularDigitoVerificador(numeroBase);
        return String.format("%,d-%d", numeroBase, digitoVerificador).replace(",", ".");
    }

    private static int calcularDigitoVerificador(int numero) {
        int suma = 0;
        int multiplicador = 2;
        while (numero > 0) {
            int digito = numero % 10;
            suma += digito * multiplicador;
            multiplicador = (multiplicador == 7) ? 2 : multiplicador + 1;
            numero /= 10;
        }
        int resto = 11 - (suma % 11);
        if (resto == 11) {
            return 0;
        } else if (resto == 10) {
            return 'K';
        } else {
            return resto;
        }
    }
}

