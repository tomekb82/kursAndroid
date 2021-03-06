

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

- zasoby dostepne wewnatrz aplikacji

- przechowywanie obiektow dostepnych dla aplikacji, ale niezaleznych od konfiguracji (jak grafiki zalezne od rozdzielczosci), zawsze ladownae sa tak samo

- wklejamy zawartosc gotowych assets do katalogu /app/src/main

- odczyt danych/pliku z assets:
    InputStream inputStream = ctx.getAssets().open(filename);



## Parsowanie JSON

- pobieranie jsona jako String, parsowanie Stringa na JsonArray, przepisanie JsonArray na nasz obiekt Java:

    String json = loadStringFromAssets(ctx, "solar.json");
    JSONObject jsonObject = new JSONObject(json);
    JSONArray jsonArray = jsonObject.getJSONArray(type);
    SolarObject[] solarObjects = new SolarObject[jsonArray.length()];
    for(int i=0; i< jsonArray.length(); i++){
        SolarObject solarObject = new SolarObject(jsonArray.getJSONObject(i));
        solarObjects[i] = solarObject;
    }


## GridLayoutManager

- siatka obiektow 

- na RecyclerView oparty

- utworzenie managera layoutu dla gdir, podanie jako parametry aktualnego activity oraz liczby kolumn grida(2)
    objectRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        
- wykorzystanie ButterKnife w Holderze dla adaptera w celu dobrania sie do wartosci z widoku i aktualizacji danych na nim


- wagi elementow

## Karty

- pozwalaja grupowac elementy

- cardview trzeba dodac, jest to zewnetrzna biblioteka


## Glide

- asynchroniczne ladowanie obrazkow w widokach

- widoki maja byc szybkie

- dodajemy jako dependencje w projekcie

- Glide.with(kontekst).load(sciezka z assets).into(w jakim imageview imiescic obrazek)


## Placeholdery

- new drawable resource file : Shape - prosty ksztalt


## Obsluga zdarzen

- obsluga klikniecia realizowana na poziomie ViewHoldera ustawiajacego listenera na klikniecie,
wolana jest metoda adaptera, ktora przekazuje klikniety obiekt dalej

- activity lub fragment moze nasluchiwac na zdarzenie klikniecia na konkretny przycisk aby dowiedziec 
sie jaki element zostal klikniety - realizuje sie to poprzez deklaracje interfejsu w adapterze oraz seetera ustawianego w activity/fragmencie,
jezeli przycisk zostal klikniety wywolywana jest metoda z adaptera

## ViewPager

- widok pozwalajacy opakowywac widoku lub fragmenty i przelaczy sie miedzy nimi przy uzyciu gestu Swipe

- PagerAdapter rozszerza FragmentStatePagerAdapter - sluzy do przechowywania framgentow, przechowuje stan fragmentow miedzy przelaczeniami

- getChildFragmentManager() - inny niz dla activity, fragmenty maja swojego managera

- utworzenie metody newInstance - przy tworzeniu instancji zapamietujemy obkeity

- podczas podpinania widoku wyciagamy obiekty, tworzymy adatper oraz ustawiamy na viewPagerze

- zaczynamy od stworzenia nowego fragmentu i wewnatarz tego layoutu dodajemy element ViewPager


## Tab Layout

- mozliwosc wyswietlania belki - na ktorym fragmentcie jestesmy

- nad ViewPagerem dodajemy TabLayout

- zmiany w ViewPagerze powinny powodowac zmiany w TabLayout: moonsTabLayout.setupWithViewPager(moonsViewPager);

- dodanie tytulu na belce: implementacja metody getPageTitle w adapterze ViewPagera

- android:background="?attr/colorPrimary"  - ustawienie aktualnej wartosci koloru pod stala colorPrimary

- ustawienia opcji:
    xmlns:app="http://schemas.android.com/apk/res-auto"
    app:tabGravity="fill"
    app:tabMode="scrollable"


## Nowe lepsze podejscie do TabLayout

- zgodne ze standardem google (Material Design) 

- przeniesc TabLayout do glownego activity i ukrywac lub nie w zaleznosci od potrzeb (ponizej Toolbar)

- onAttach() - wolana gdy fragment jest dodawany do activity, do metody przekazywany jest kontekst okreslajacy activity do ktorego fragment zostanie podpiety

- onDetach() - wolana w momencie gdy fragment usuwamy z activity

- tworzymy interfejs z dwoma metodami show/hide wolanymi w onAttach/onDetach

- glowne activity implementuje powyzszy interfejs i w metodach ukrywa badz nie TabLayout ,dodatkowo przekazywany jest ViewPager ktory podpinamy w activity to TabLayoutu


### Widok Detail

- wykorzytamy CoordinatorLayout oraz NestedScrollView - fajne efekty paralaksy, efekty zanikania pewnych widokow w momencie kiedy scrollujemy

- tworzymy nowe activity: ScrollingActivity

-- wykorzystujemy HierarchicalParent - wskazujemy rodzica w hierarchii dla tego elementu, 

- uruchomienie activity: metoda start, przyjmujaca jako parametr Activity, tworzymy Intent i startujemy activity przez activity.startActivity(intent)

- startujemy activity we fragmencie przez activity.start();

- CoordinatorLatout - zarzadza roznymi widokami activity jezeli scrollujemy

-- rozszerzenie obrazu na cala szerokosc: android:adjustViewBounds="true"

-- wycentrowanie i przyciecie elementu obrazka: android:scaleType="centerCrop"

- parsowanie tagow HTML ze Stringa: Html.fromHtml(text)

- dostosuj ImageView w CoordinatorLayout aby zachodzil na cale okno

## Osadzanie RecyclerView w Activity


- wyswietlanie elementow w RecyclerView w pozycji horyzontalnej

-- istotne ze z pozycji horyzontalnej RecyclerView nie wie jaka jest szerokosc
wiec musimy ja jawnie okreslic np.220dp (item 200dp + marginesy)

- reuzycie istniejacego adaptera do wyswietlania obiektow, ale w innej roli

- ustawienie layout managera na liste horyzontalną:
    moonsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false));

- obiekty w ScrollView umieszczamy zawsze w LinearLayout

- stylowanie karty obiektow

-- rozszeczenie widoku aby dopasowac obrazek: android:adjustViewBounds="true"
-- aby caly obrazek znalazl sie w ImageView: android:scaleType="centerInside"


## Hack na poprawne dzialanie efektu paralaksy (problem biblioteki)

- problem przy dwoch obiektach scrollowanych, gdzie jeden jest zagniezdzony w drugim, wtedy na tym drugim nie dziala efekt paralasky

- rozwiazanie: na wewnetrznym obiekcie wylaczamy scrollowanie
    moonsRecyclerView.setNestedScrollingEnabled(false);



## Obsluga klikniecia na obiekty

- realizowana przez podpiecie listenera do naszego adaptera
oraz implementacje metody uruchamiajacej inne activity


## Dodanie Floating Option Button do wyswietlania filmow

- ustawienie ikony: dodajemy VectorAsset

- dodanie obslugi do FAB

- uwaga: CoordinatorLayout potrafi sam zarzadzac widocznoscia elementow na widokach i nalezy samemu, recznie to wylaczyc, aby np. moc ukryc przycisk FAB

-- w tym celu odpinamy nasz widok od CoordinatorLayoutu, wtedy widocznosci nie beda napisywane
    CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
    lp.setAnchorId(View.NO_ID);



## Zapisywanie tapety

- przeslaniamy w activity metody: onCreateOptionsMenu oraz onOptionsItemSelected

- usluga WallpaperManager - pozwala zarzadzac tapetami wewnatrz telefonu

- pobieramy tapete z assetow:
    wallpaperManager.setStream(getAssets().open(image))

- wymagane uprawnienie normalne, w manifescie dodajemy uprawnienie:

    <uses-permission android:name="android.permission.SET_WALLPAPER"/>

## Uprawnienia 

Są 2 typy:
- 1) normalne - deklaruje się tylko w manifescie
- 2) niebezpieczne - w trakcie dzialania uzytkownik musi sie zgodzic czy chce cos zrobic

## PODSUMOWANIE

- aplikacja wykorzystuje nawigacje z wykorzystaniem NavigationDrawer

- wyswietla widoki przy uzyciu RecyclerView

- asynchroniczne ladowanie obrazkow z wykorzystaniem biblioteki Glide

- ViewPager do przelaczania widokow 

- wykorzystywane fragmenty - do calej nawigacji zewnetrznej jak i poszczegolnych widokow NavigationDrawera

- dostep do assetow, zmiana obrazkow, parsowanie JSON

- ustawienia video, tapety


## Tablety

- jak uzywac fragmentow po to aby pewne fragmenty moc wykorzystywac na roznych urzadzeniach

- na androidzie nie ma funkcjonalnosci umozliwiajacej czy urzadzenie jest tabeletem

- kwalifikatory large i xlarge - aktualnie niewykorzystywane

- aktualnie okresla sie rozroznienie kwalikatorami sw600dp(tablet 7calowy) i sw720dp(tablet 10 calowy)

- fragmenty - wykorzystywanie pewnych wiokow wielorotnie w zaleznosci jakie mamy urzadzenie.


- 
