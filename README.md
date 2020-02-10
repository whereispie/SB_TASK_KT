# SB_TASK_KT
task for SBT

Задание.
Разработать консольное приложение, реализующее алгоритм:

1.1.    Создать таблицу WORDS с полями: WORD, WORD_COUNT.
            Разработать консольное приложение, реализующее алгоритм:
2.1.    Принимает параметр – имя файла. Файл - текст utf8.
2.2.    Парсит файл, выделяет слова, состоящие из букв кириллицы, латиницы и цифр. Остальные знаки считаем разделителями.
2.3.    По найденному слову ищет запись в таблице:
2.3.1.      если запись отсутствует, добавляется со значениями: WORD = найденное слово, WORD_COUNT = 1
2.3.2.      если запись найдена, WORD_COUNT  увеличивается на 1.
3.      Предусмотреть возможность выполнения нескольких процессов парсинга с общей БД.


Реализация:
- БД:MongoDB кластер (512 mb). Для работы есть удобное IDE (MongoDB Compass). Данные для логина на кластер указаны
в config.properties. Доступ к БД по умолчания установил - 0.0.0.0/0, т.е. для всех. 
- ЯП:Kotlin
- Основной формат: JSON

Основной алгоритм:
1. Парсинг файла в ArrayList (кириллица, латиница и цифр(int,float) + слова со смежными символами типа ,слово,))
2. Подключение к MongoDB
3. Забираем данные из основной коллекции (таблицы)
4. Парсинг в JSON для дальнейшей манипуляции с данными
5. Парсинг в HashMap (разбиение на 2 коллекции: 1 - для обновления сущ. слов, 2 - для новых слов )
5. Разбиение HashMap на две коллекции: 1 - для обновления word_count слова, 2 - для новых слов )
6. Обновление/вставка новых слов в БД
7. Закрытие соединения

Замечания:
0. Выбор файла сделан через consoleRead() для удобства пользования
1. Не успел обработать вариант парсинга слов в виде "Луна-13".
2. Для O(1) вставки и удаления в БД нужно доработать вставку/удаление по средствам JSON
3. Возможность выполнения нескольких процессов парсинга с общей БД в Mongo реализованно опционально.
Транзакции (работы с мульти документами) атомарны по умолчанию (есть API для тюнинга, но так-же не хватило времени проверить). 
Кластер разбит на 3 реплики.
4. Работоспособность проверил в Unix(macOS Catalina 10.15.3) и Windows(x64 10) среде.
5. Логирование и пояснения убраны для удобства восприятия 