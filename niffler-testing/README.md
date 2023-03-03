## HW1:

#### Create User through Niffler UI:

```agsl
username: dima
password: 12345
```

#### Categories and Spends will be created automatically during execution using SuiteExtension:

    Category: list of category: niffler-testing/src/main/java/niffler/model/CategoryValues.class
    Spends:  three random spends will be created.

#### For Execute in IntelliJ IDEA select run configuration :

    'spend test with AllureServe'

#### For Execute in IntelliJ IDEA select run configuration :

    'spend test with AllureServe'

## HW2:

    1) Посмотреть эксперементально – написав код, – на каком этапе жизненного цикла Extension работают методы ExtensionContext.getRequiredTestMethod(), ExtensionContext.getRequiredTestClass(), ExtensionContext.getRequiredTestInstance()

LifeCycleTests created. Done

    2) Дописать апи тесты на обновление профайла – напрямую в юзердату, – тесты должны использовать ArgumentConverter для параметра UserJson. Если вы используете чаще ReatAssured, то попробуйте сделать ДЗ на Retrofit и наоборот.



    3) Дополнить UsersExtension возможностью указать двух юзеров разных типов в одном тесте. То есть мы исходим из допущения, что одному тесту необходимы сразу два юзера (скрин ниже). UsersExtension должен обеспечивать резолв обоих юзеров, дожидаясь пока нужная для теста комбинация не станет доступна в очередях