

## Zalozenia


- model aplikacji master/detail - dwa activity, glowne i szczegoly

- biblioteka DesignSupport - umozlwia wsparcie dla funkcji dostepnych normalnie tylko w nowszych wersjach androida

- framgenty - tablety, nawigacja (NavigationDrawer)

- asynchroniczne ladowanie grafik


## Navigation drawer 

- nawigacja wysuwana po prawej stronie

- przycick hamburgera

- katalog menu: konfiguracja menu aplikacji

### generowanie ikonek

- generowanie grafiki wektorowej: Vector Asset -> Choose


### dodanie grafiki do projektu


- wklejenie grafiki w roznej rozdzielczosci do katalogu /res projektu

- NavigationView - wysuwana czesc menu, posiada dwa parametry:

-- headerLayout - odpowiada co jest wyswietlane na gorze

-- menu: odpowiada co jest wyswietlane jako menu

- FrameLayout

- adjustViewBounds - aby obrazek zajmowal pelna szerokosc, element dopasuje sie wielkoscia do calego elementu 

- usuniecie domyslnych paddingow glownego layoutu FrameLayout
--

### ustawienie domyslnie wybranego przycisku na menu

- w glownym activity w metodzie onCreate() ustawiamy 
    navigationView.setCheckedItem(R.id.nav_planets) 
oraz musimy wywolac metode  
    onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_planets))


## Fragmenty

- wprowadzone w wersji 3.0 androida 

- jedno actvity, wykorzystywane przy menu

- mozna je dynamicznie zmieniac, przekazywac do nich parametry

- tworzenie nowego fragmentu: New->Fragment-> Blank 

-- kazdy fragment musi miec bezparametrowy konstruktor	

-- onCreateView() - sluzy do stworzenia fragmentu, mozna zwrocic nulla,
bo fragmenty nie wymagaja widoku (w odroznieniu od Activity)

- tworzenie instancji fragmentu
    SolarObjectsFragment fragment = new SolarObjectsFragment();

- wyswietlenie fragmentu - uzywamy fragment managera
-- getFragmentManager() - dla nowszych wersji
-- getSupportFragmentManager() - dla starszych wersji

- wszystkie operacji na fragmentach musimy wykonac w obrebie TRANSAKCJI
- okreslamy miejsce gdzie ma sie wyswietlic fragment, gdzie chcemy go dodac

    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.add(R.id.containterLayout, fragment);
    fragmentTransaction.commit();

- uwaga: nalezy uzywac replace() zamiast add() - podmieniamy fragmenty zamiast dodawac/nakladac jeden na drugi

- uwaga: nie nalezy przekazywac zadnych parametrow do fragmentow przez konstruktor, nie jest zalecane

- stosujemy wzorzec metody fabryki w celu przekazywania parametrow

-- zamiast tworzyc konstrutor, tworzymy metode publiczna, statyczna newInstance (wystarczy wpisac newInstance i android studio nam samo wygeneruje)

-- w metodzie przekazujemy takie parametry ktore chcemy przekazac (jakie chcialoby sie miec w konstruktorze),
zadaniem metody jest stworzenie fragmentu i ustawienie na nim odpowiednich argumento, ale nie z wykorzystaniem konstruktora ale przy wykorzystaniu Bundle(sa przechowywane i moga byc przekazywane miedzy instancjami)

-- do Bundle przekazyjemy obiekty przez metode  
    Bundle args = new Bundle();
    args.putSerializable(OBJECTS_KEY, objects)

-- odczyt argumentow z Bundle: getArgument()
    getArguments().getSerializable(OBJECTS_KEY)

- wyswietlanie elementow fragmentu

-- w metodzie onViewCreated()  -  otrzymuje juz utworzony widok


## FrameLayout 

- widok w ktorym mozemy przechowywac pojedynczy element


- obsluga przelaczania sie miedzy fragmentami


## Odczyt zasobow z ASSETS
