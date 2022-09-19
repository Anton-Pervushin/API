#	Реализация модели клиент-серверного банкомата.
##	1. Постановка задачи

Реализовать работу банкомата в виде клиент-серверного приложения.   
Данные о балансе пользователей хранятся в файловой системе компьютера.

Поддерживаются следующие операции:
* Запросить баланс для пользователя по его ID;
* Обновление баланса (перевод средств между счетами разных клиентов);
* Снятие заданной суммы с баланса для пользователя по его ID;
* Пополнение баланса (зачисление средств на свой счет);

Основные операции банкомата работают корректно в многопоточной среде, т.е. учитывают, что все транзакции над одним счетом клиента могут происходить одновременно
## 2. Каким образом работать с проектом?
* Фреймворк для сборки проекта – Maven (mvn clean package)
* Проект использует файл service.properties, в котором указан путь до файла с базой карт. По умолчанию проект ищет файл с базой в папке запуска программы.
* Для запуска проекта используйте следующую команду:  
  `java -DserviceProperties=service.properties -jar ATM_Web_SkillFactory.jar `

## 3. Структура файла базы карт
Каждая карта в базе содержит следующую структуру:  
`ID карты: Имя пользователя: Сумма средств на счету: PIN код`  
Пример:  
`0:Aleksey:1000:1234`

## 4. Структура запросов к веб серверу
#####  Получение баланса карты по id
Тип запроса : `GET`  
Адрес: `/operations/{id}`  
Структура ответа : `{"result":"Операция успешна","cardBalance":"2000"}`

#####  Добавление средств на карту
Тип запроса : `POST`  
Адрес: `/operations/add`   
Структура тела запроса :  `{"cardId":1, "funds": 1500}`  
Структура ответа : `{"result":"Операция успешна"}`

#####  Снятие средств с карты
Тип запроса : `POST`  
Адрес: `/operations/withdraw`   
Структура тела запроса :  `{"cardId":1, "funds": 1500}`  
Структура ответа : `{"result":"Операция успешна"}`

#####  Перевод средств на другую карту
Тип запроса : `POST`  
Адрес: `/operations/transfer`   
Структура тела запроса :  `{"cardIdFrom":1, "cardIdTo":2, "funds": 1500}`  
Структура ответа : `{"result":"Операция успешна"}`

## 5. Список основных классов проекта
Пакет runner, список классов:
* Runner, отвечает за запуск работы приложения
* BankApplicationConsole, отвечает за запуск работы банкомата
* ScannerWithValidation, отвечает за считываение пользовательских данных с консоли

Пакет card, список классов:
* ClientSession, содерждит сессионные данные пользователя
* UserCard, реализует сущность банковская карточка пользователя
* UserCardOperations, реализует банковские операции с картами
* CardArray, обертка над коллекцией пользовательских карт системы
* ResultOperation, результат клиентской операции
* OperationsEnum, данные для отображения пунктов в меню

Пакет variables
* ServiceProperties класс с константами
* LoadProperties класс для работы с пропертями

Пакет runner
* OperationController контроллер запросов к веб серверу
* MainRunner точка входа программы

Пакет files
* FileUtil, реализует работу базовых функций по работе с файлами (чтение, запись)
* ProjectFileWorker, реализует валидацию файловой базы данных, чтение данных из файла в коллекцию CardArray и запись данных в файл из коллеции CardArray
