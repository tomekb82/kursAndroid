

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
