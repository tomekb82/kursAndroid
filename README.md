# Kurs Android

## Instalacja android sdk
https://developer.android.com/studio/install.html

## Intellij IDEA (Android Studio) - przydatne klawisze

- Alt + Enter  - utworzenie metody w activity

- Ctrl + Alt + V - ekstrakcja zaznaczenie w zmienna lokalna

- Ctrl + Alt + M - ekstrakcja metody
 
- Ctrl + Alt + C - ekstrakcja stalej

- Ctrl + Alt + F - ekstrakcja pola klasy

- Ctrl + Alt+J  lub Ctrl+Alt+T  - generacja automatyczna kodu (pętle, try)

- Ctrl + F6 - refaktoryzacja kodu

- F6 - move, np. przeniesienie pól z jednej klasy do drugiej


## Layouts

- 'match_parent'/'wrap_content'

- 'gravity: end' - ustawienie tekstu do prawej strony

- 'shrinkColumns'/ 'stretchColumns' - dostosowanie rozmiaru kolumn do rozmiaru ekranu podajac numery kolumn np.: '0,1,2,3'

- 'alignParent: bottom' oraz 'allignComponent' - przyczep do dolu ekranu w realative layout oraz usuwamy sile przez allignComponent

- 'gravity: center_vertical' - wysrodkowanie pola na srodku obszaru


## Zdarzenia - java

- findViewById(id) - metoda pozwalajaca dostac sie do elementu ktory nas interesuje, np. 'R.id.textView' - resource id o nazwie textView

- tworzenie stylow: Refaktor-> Extract -> Style i wybieramy atrybuty dodawane do stylow

## Obsluga obrotu ekranu

- onSaveInstanceState

- onRestoreInstanceState


## Dodawanie layoutow dedykowanych do rozmiaru ekranu

Widok Design layoutu, pierwsza ikonka na gorze z lista rozwijana:

-> Create Landscape Variation

## Stylowanie

- zmiany w edytorze tekstowym

- dodajemy 'android:background = "@color/display__background", nastepnie Alt+Enter oraz 'Create color value resource display_background'

- zmiana koloru czcionki: 'android:texColor="@android:color/white" '
zamiast #FFFFFF

- zmiana rozmiaru czcionki (w SP): 'android:textSize="50sp"'

- DP/DPI - rozmiar elementu, SP - dla rozmiaru czcionki

- tylko jedna linia dla wartosci: 'android:singleLine="true"'

- padding: zmiana odstepu od krawedzi

- zmiana domyslnych odstepow - przez usuniecie domyslnych paddingow dodanych w layoucie

- zmiana koloru naglowka (w pliku colors.xml) PrimaryColor, PrimaryColorDark


- stylowanie buttonow ( w pliku styles.xml): dodajemy parenta dla naszego, istniejacego stylu na 'parent = "Base.Widget.AppCompat.Button.Borderless" '

- zmiana koloru buttonow: 'android:background'

- dodanie selektorow np.zmiana selectora dla buttonow przy wcisnieciu dla atrybuti 'android:background', przykladowo:
    
    <item name="android:background">@drawable/button_selector</item>
   
- selektor przelaczy w zaleznosci od tego co zostanie wcisniete (np.'android:state_pressed="true"')    

- efekt 'ripple' (rozchodzenia sie fali, dostepny tylko dla nowych wersji androida)- w tym celu nalezy utworzyc selektor ladowny tylko dla konkretnej wersji systemu -> dodajemy katalog 'drawable' z kwalifikatorem 'Version' na ktorym bedziemy go odpalali - wtedy nasz obiekt bedzie ladowany od konkretnej wesji SO.

- efekt ripple - dodany w Material Design, jak jeden z tamtych gadzetow

- podstyle - tworzenie podstyli poprzez kopiowanie istniejacych selektorow oraz ich modyfikacje


## Collor Pallete app

- Blank/Basic Activity: PaletteActivity

-- menu layoutu ustawionw w resource 'R.menu.menu_palette' i ladowane w onCreateOptionsMenu()

-- zawiera Floating Action Button

-- przejscie do drugiego activity

-- LinearLayout zamiast RelativeLayout oraz opcja 'android:orientation="vertical"' - bo chcemy aby elementy byly jeden pod drugim

-- parametryzacja stringow: do metody getString() podajemy resource string oraz parametry, przykladowo:

    getString(R.string.new_color_created, colorInhex)

w string.xml:
    <string name="new_color_created">New color created: %s</string>    

-- etyketa: TextView

-- SeekBar
--- ustawienie wartosci maksymalnej: w stylach podajemy "android:max"



- Empty Activity (jako drugie w projekcie): ColorActivity 

-- Zapamietywanie wartosci miedzy uruchomieniami: onSaveInstanceState(), onRestoreInstanceState

### Przejscia miedzy roznymi activity

- startActivity(ActivityZ.this, ActivityDo.class)

- obiekt Intent (intencja) przekazana do startActivity()


### Przesylanie danych miedzy Activity

- startActivityForResult)( - mowi ze chcemy przekazywac dane w Activity
- setResult() - ustawiamy wartosci
- onActivityResult() - odbieramy dane przekazane z Activity

- startActivityForResult(), setResult(): metody pozwalajace przesylac dane w jedna i druga strone

- przekazywane dane dodajemy w obiekcie Intent, ktory przekazujemy jako parametr do setResult()

- finish() - oznacza zakonczenie Activity, odpowiada akcji WSTECZ,
uzywana jezeli chcemy aby dane od razu zostaly wyslane w po wywolaniu setResult()

- obsluga odebranych danych w onActivityResult()

### Wyświetlanie listy: RecyclerView  + Adapter

- nastepca ListView, Gallery, GridView laczycy cechy poprzednikow

- RecyclerView dostepny od wersji android 5.0, jako zewnetrzna biblioteka (trzeba osobno zainstalowac przez OpenModuleSettings -> recyclerview-v7)

- Rec - zajmuje sie tylko zarzadzanie widokami, 

- Adapter - okresla jakie dane i w jaki sposob maja zostac wyswietlone(wyglad)

-- extends RecyclerView.Adapter

-- klasa adaptera jest parametryzowana klasa Holdera

-- zawiera 3 metody:

--- onCreateViewHolder() - tworzy widok i opakowuje w Holder

--- onBindViewHolder() - odpowiada za przypisanie danych do konkretnego wiersza, laczy widok z danymi, dla wierszy wyświetla dane 

--- getItemCount() - zwraca liczbe elmentów wewnetrzych adaptera - ilosc elementow listy 


-- Holder - klasa przechowujaca informacje o poszczegolnym wierszu

-- LayoutInflater - klasa potrafiaca przetworzyc widoki w strukture obiektow

-- nalezy do adaptera przekazac LayoutInflater, na przyklad w konstruktorze klasy adaptera

- LayoutManager - okresla kolejnosc w jaki maja zostac wyswietlone


- Aby poinformowac ze dane sie zmienily trzeba uzyc metody notifyXXX()

### Gest Swipe - usuwanie elementow z listy

- ItemTouchHelper.SimpleCallback()

- definiujemy olgike w metodzie onSwipe()

- podpinamy pod obiekt ItemTouchHelper callbacka oraz RecyclerView

### Edycja z RecyclerView

- nie ma informacji ktory element został klikniety w RecyclerView, trzeba to samemu obsluzyc - przypinamy onClickListener na poszczegolnych wierszach, na poziomie ViewHoldera (implements OnClickListener)

- podpinamy onClickListenera do ViewHoldera w kontruktorze, podpinamy rowniez adapter oraz w onClick z holderze wolamy funkcje obslugi w adapterze

- adapter moze dzieki temu zareagowac na klikniecie na okreslonej pozycji

- podsumowujac: w viewHolderze obslugujemy klikniecie na widoku, klikniecie powoduje wywolanie metody na adapterze z wybrana pozycja, metoda w adapterze sprawdza czy mamy podpiety listener, jezeli tak do wywolujemy odpowiednia metode z wartoscia kliknietj pozycji

- pozniej tylko startujemy activity_: tworzymy intent i startActivityForResult()

- w odbiorczym activity pobieramy wartosc przez getIntent()


### Dodawanie akcji

- w menu dodajemy itemy


## Dodawanie/generowanie wbudownych ikon

- New -> Image Asset : generator zasobow graficznych

- showAsAction: ustawia czy element ma sie wyswietlac jako ikona czy tekst

- po dodaniu akcji dodajemy ja w onOptionsitemSelected() i zwracamy 'true', 
ze nie trzeba szukac obslugi w innych klasach

## Cykl zycia activity

- stos Activity

- rozne metody pozwalajace na reagowanie na pewne zdarzenia w cyklu zycia actovity (onStart, onCreate, onResume, onPause, onStop, onDestroy)

- cykl zycia nie jest liniowy (pojawia sie i znika)

- dodanie logow do podgladu kolejnosci wywolania activity

-- Log.d(PaletteActivity.class.getSimpleName(), "onDestroy")

-- logcat - zawiera komunikaty uzytkownika
 
-- filtrowanie logow po klasie


### Obsluga przycisku Up (przejscie w gore)


- przejscie w gore: NavUtils.navigateUpFromSameTask(this)

- musimy tylko okreslic miejsce przejscia w pliku AndroidManifest.xml
przez 'android:parentActivityName=".PaletteActivity"'  -> dostepna tylko dla wersji 5.x  lub dodajemy meta-data dla starszych wersji

    <meta-data android:name="android.support.PARENT_ACTIVITY" android:value=".PaletteActivity"/>

## Floating Action Button

mozliwosc przechodzenia miedzy activity

## Tłumaczenia

- stringi przenosimy do values - aby umozliwic tlumaczenie

- dodanie tlumaczenia: z pliku strings.xml -> Open Editor po prawej stronie okn
a -> Kula ziemska


## ButterKnife

- zamiast tworzenia pola i przypisania z uzyciem findViewById() mozna uzyc gotowej biblioteki ButterKnife

- dodawanie biblioteki: Open Module Settings -> dependencies w module app -> Library -> butterknife

- generuje kod na podstawie adnotacji, ktory musielibysmy napisac recznie, usunac 'private' na wersje 'public'

- adnotacje @BindView

- konieczne uruchomienie butterKnife: ButterKnife.bind(this);i


## Instalacja Pluginu ButterKnife w AndroidStudio

Settings -> Browse repositories: Andreoid ButterKnife Zelezy


- uwaga: istotne dodanie samego butterknife'a oraz compilera butterknife

- (Alt + Insert) na layout activity -> generate ButterKnife injections  - pojawia sie okno z mozliwoscia dodania mapowania

- ButterKnife.setDebug(true) - przydatne przy debugu jak cos nie dziala

## Obsluga/implementacja onClick

- dodajemy ze klasa Activity implements View.onClickListener

- przez ButterKnife: @OnClick(R.id.generateButton)

## Shared Preferences

- przechowywanie danych, persytowanie danych po kazdym restarcie aplikacji,
dane zostaja po wylaczeniu i wlaczeniu aplikacji

- pozwala przechowywac dane miedzy uruchomieniami

- zapis i czyszczenie w shared preferences

- dodajemy jako parametr w konstruktorze Adaptera

- sharedPreferences - to plik xml przechowywany w naszym katalogu aplikacji,

- dodawanie domyslnego managera: PreferenceManager.getDefaultSharedPreferences()

- zapis:

    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("colors", jsonArray.toString());
    editor.apply();

- odczyt: sharedPreferences.getString(COLORS_KEY, "[]")

- czyszczenie: sharedPreferences.edit().clear().apply();

### Terminal 

- zakladka terminal

- ograniczone uprawnienia na urzadeniu, w emulatorze pelne uprawnienia root'a

- 'adb shell' - powloka systemu Linux  

- katalog aplikacji: '/data/data'

### JSON

JSONArray


### Pallete

- biblioteka Palette: dodajemy jako dependency (jedna z domyslnych)

- pozwala na podstawie koloru generowac na przyklad kolor tekstu dobierajac
kontrast w taki sposob aby byl dobrze widoczny (np. na tle obrazka/zdjecia)

-  analizuje kolory i odpowiednio zmienia

- new Palette.Swatch(color, 1).getTitleTextColor()

### Undo

- dodany jako akcja w SnakBarze (dodano listener i obsluge)


### ScrollView

- umozliwia przeskorolowanie ekranu jezeli bedzie taka potrzeba,
jezeli cos sie nie zmiesci

- opakowujemy LinearLayout przez ScrollView 

- ScrollView moze miec tylko JEDNEGO POTOMKA

- wewnetrzny LinearLayout powinien miec ustawiony 'android:layout_height="wrap_content"' aby ScrollView sam decydowal co i jak scrollowac



- 
