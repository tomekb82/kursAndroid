# kursAndroid

## instalacja android sdk
https://developer.android.com/studio/install.html

## uruchomienie android sdk


## przydatne klawisze

- Alt + Enter  - utworzenie metody w activity

- Ctrl + Alt + V - ekstrakcja zaznaczenie w zmienna lokalna

Ctrl + Alt + M
 
Ctrl + Alt + C

Ctrl + Alt + F

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

- Empty Activity (jako drugie w projekcie): ColorActivity 


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



## Floating Action Button

mozliwosc przechodzenia miedzy activity


## Tłumaczenia

## Shared Preferences

przechowywanie danych, persytowanie danych po kazdym restarcie aplikacji,
dane zostaja po wylaczeniu i wlaczeniu aplikacji

 
