package com.example.testkfu2


import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver


//fun main() {
//    // URL страницы
//    val url = "https://kpfu.ru/sveden/employees/"
//
//    // Загружаем страницу с диагностикой
//    val response = Jsoup.connect(url).execute()
//    val rawHtml = response.body() // Исходный текст страницы
//    val rawBytes = response.bodyAsBytes() // Сырой байтовый массив страницы
//
//    // Печатаем данные для диагностики
//    println("Исходный HTML (частично):")
//    println(rawHtml.substring(0, 1000)) // Показываем первые 1000 символов HTML
//
//    println("\nСырой HTML в байтах (первые 50):")
//    println(rawBytes.take(50)) // Показываем первые 50 байтов
//
//    // Пробуем разные кодировки
//    val document: Document = Jsoup.parse(String(rawBytes, charset("Windows-1251")))
//    println("\nРаспарсенный HTML с кодировкой Windows-1251 (частично):")
//    println(document.html().substring(0, 1000)) // Печатаем распарсенный HTML
//
//    // Проверка, удаётся ли найти таблицу
//    val rows = document.select("table tbody tr")
//    println("\nКоличество строк в таблице: ${rows.size}")
//}



fun main() {
    System.setProperty("webdriver.chrome.driver", "C:/path/to/chromedriver")

    // Создаём экземпляр WebDriver
    val driver: WebDriver = ChromeDriver()

    try {
        // Открываем сайт
        driver.get("https://kpfu.ru/sveden/employees/")

        // Ждём загрузки страницы (если требуется ожидание JavaScript, добавьте Thread.sleep или WebDriverWait)
        Thread.sleep(5000) // Временно, лучше заменить на ожидание элемента через WebDriverWait

        // Получаем исходный код страницы
        val pageSource = driver.pageSource

        // Парсим содержимое страницы через Jsoup
        val document = Jsoup.parse(pageSource)

        // Ищем таблицу сотрудников
        val rows = document.select("table tbody tr")
        println("Количество строк в таблице: ${rows.size}")

        for (row in rows) {
            val cells = row.select("td")
            if (cells.size >= 6) {
                val position = cells[1].text()
                val educationLevel = cells[2].text()
                val specialization = cells[3].text()
                val qualification = cells[4].text()
                val academicDegree = cells[5].text()
                val academicTitle = cells[6].text()

                println(
                    "Должность: $position, Образование: $educationLevel, Специализация: $specialization, " +
                            "Квалификация: $qualification, Учёная степень: $academicDegree, Учёное звание: $academicTitle"
                )
            }
        }
    } finally {
        // Закрываем браузер
        driver.quit()
    }
}