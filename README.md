# kursAndroid

## instalacja android sdk
https://developer.android.com/studio/install.html

## przydatne klawisze Intelij Idea (Android Studio)

- Alt + Enter  - utworzenie metody w activity

- Ctrl + Alt + V - ekstrakcja zaznaczenie w zmienna lokalna

- Ctrl + Alt + M - ekstrakcja metody
 
- Ctrl + Alt + C - ekstrakcja stalej

- Ctrl + Alt + F - ekstrakcja pola klasy

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


## Collot Pallete app

- Blank/Basic Activity: PaletteActivity

-- menu layoutu ustawionw w resource 'R.menu.menu_palette' i ladowane w onCreateOptionsMenu()

-- zawiera Floating Action Button

-- przejscie do drugiego activity

-- LinearLayout zamiast RelativeLayout oraz opcja 'android:orientation="vertical"' - bo chcemy aby elementy byly jeden pod drugim


-- etyketa: TextView

-- SeekBar
--- ustawienie wartosci maksymalnej: w stylach podajemy "android:max"



- Empty Activity (jako drugie w projekcie): ColorActivity 

-- Zapamietywanie wartosci miedzy uruchomieniami: onSaveInstanceState(), onRestoreInstanceState

### Przesylanie danych miedzy Activity




### Przejscia miedzy roznymi activity

- startActivity(ActivityZ.this, ActivityDo.class)

- obiekt Intent (intencja) przekazana do startActivity()



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

przechowywanie danych, persytowanie danych po kazdym restarcie aplikacji,
dane zostaja po wylaczeniu i wlaczeniu aplikacji

 
