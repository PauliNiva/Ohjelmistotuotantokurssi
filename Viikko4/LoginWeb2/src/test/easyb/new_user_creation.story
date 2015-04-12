import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description "A new user account can be created if a proper unused username and a proper password are given"

scenario "creation successful with correct username and password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    when 'a valid username and password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("Pauli");
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasan4");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasan4");
        element = driver.findElement(By.name("add"));
        element.submit();
    }

    then 'new user is registered to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe true
    }
}

scenario "can login with succesfully generated account", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    when 'a valid username and password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("Paulii");
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasan4");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasan4");
        element = driver.findElement(By.name("add"));
        element.submit();
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();
        element = driver.findElement(By.linkText("login"));
        element.click();
        element = driver.findElement(By.name("username"));
        element.sendKeys("Paulii");
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasan4");
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    then  'new credentials allow logging in to the system', {
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe true
    }
}

scenario "creation fails with correct username and too short password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    when 'a valid username and too short password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("Pau");
        element = driver.findElement(By.name("password"));
        element.sendKeys("sa4");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("sa4");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
    then 'new user is not registered to the system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
    }
}

scenario "creation fails with correct username and password consisting of letters", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    when 'a valid username and password consisting of letters are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("Pauli");
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasana");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasana");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
    then 'new user is not registered to the system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
    }
}

scenario "creation fails with too short username and valid password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    when 'too short username and valid password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("Pa");
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasa4");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasan4");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
    then 'new user is not registered to the system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
        driver.getPageSource().contains("length 5-10").shouldBe true
    }
}

scenario "creation fails with already taken username and valid password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    when 'an already taken username and valid password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("Pauli");
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasan4");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasan4");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
    then 'new user is not registered to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
    }
}