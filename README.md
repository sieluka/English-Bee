# EnglishBee

**EnglishBee** to aplikacja mobilna na Androida wspierająca naukę języka angielskiego. Wykorzystuje lokalną bazę danych Room oraz zewnętrzne API słownikowe (Words.Api), oferując różnorodne formy nauki i szybki dostęp do tłumaczeń oraz quizów. Aplikacja obsługuje rejestrację i logowanie użytkownika oraz przyznaje punkty za poprawne odpowiedzi.

---

## Funkcjonalności

- **Logowanie i rejestracja** – każdy użytkownik ma własne konto i historię punktów w bieżącej sesji nauki.
- **Menu wyboru trybu nauki** – przejrzysty panel startowy z dostępem do różnych modułów.
- **Quiz gramatyczny** – testy sprawdzające znajomość gramatyki.
- **Odmiana czasowników nieregularnych** – nauka i ćwiczenia z popularnymi czasownikami angielskimi.
- **Tłumaczenie słówek** – szybkie tłumaczenie i nauka nowych słów.
- **Wyszukiwanie znaczenia słów** – integracja ze słownikiem online (Words.Api).

---

## Stos technologiczny

- **Język:** Kotlin
- **Framework:** Jetpack Compose (UI)
- **Baza danych:** Room (SQLite)
- **API:** Words.Api (dla tłumaczeń i definicji słówek)
- **Architektura:** MVVM (Model-View-ViewModel)

---

## Instalacja

1. Sklonuj repozytorium:
   ```bash
   git clone https://github.com/sieluka/English-Bee.git
   ```
2. Otwórz projekt w Android Studio.
3. Zbuduj i uruchom aplikację na emulatorze lub urządzeniu fizycznym.

Wymagania:
- Android Studio Flamingo lub nowszy
- JDK 17+
- Emulator lub urządzenie z Androidem 8.0+

---

## Konfiguracja API

Aplikacja wykorzystuje Words.Api do pobierania tłumaczeń i znaczeń słówek. W razie potrzeby skonfiguruj własny klucz API w odpowiednich plikach konfiguracyjnych projektu.

---

## Przykładowe zapytania do API

- **Pobierz wszystkie elementy**
  ```http
  GET /api/items?api_key=YOUR_API_KEY
  ```

- **Pobierz wybrany element**
  ```http
  GET /api/items/{id}
  ```

---

## Zrzuty ekranu

<h2>Zrzuty ekranu</h2>
<table>
  <thead>
    <tr>
      <th>Widok</th>
      <th>Zrzut ekranu</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Ekran ładowania</td>
      <td><img src="https://github.com/user-attachments/assets/25d20f21-c39f-4752-b2e3-3de2303f0700" alt="ekran_ladowania" width="200"></td>
    </tr>
    <tr>
      <td>Ekran startowy</td>
      <td><img src="https://github.com/user-attachments/assets/cc487d8f-266a-482b-b3a6-e91f51176d6c" alt="ekran_startowy" width="200"></td>
    </tr>
    <tr>
      <td>Logowanie</td>
      <td><img src="https://github.com/user-attachments/assets/816dea0f-4f2b-4c5b-b867-c696522ead10" alt="ekran_logowania" width="200"></td>
    </tr>
    <tr>
      <td>Rejestracja</td>
      <td><img src="https://github.com/user-attachments/assets/2f6bbb67-0c91-436a-a7e5-172588d4e059" alt="ekran_rejestracji" width="200"></td>
    </tr>
    <tr>
      <td>Błąd</td>
      <td><img src="https://github.com/user-attachments/assets/1be4551e-24f3-481a-b8be-835b08ace0ce" alt="ekran_bledu" width="200"></td>
    </tr>
    <tr>
      <td>Menu główne</td>
      <td><img src="https://github.com/user-attachments/assets/4c63aa91-16c9-46bf-b703-f6ab85e73796" alt="ekran_menu" width="200"></td>
    </tr>
    <tr>
      <td>Gramatyka</td>
      <td><img src="https://github.com/user-attachments/assets/2c0cd9b6-4044-41ba-b723-f53b9ac030d2" alt="ekran_gramatyki" width="200"></td>
    </tr>
    <tr>
      <td>Czasowniki nieregularne</td>
      <td><img src="https://github.com/user-attachments/assets/7785af33-e83a-4886-9807-bf96b5456efb" alt="ekran_czasownikow" width="200"></td>
    </tr>
    <tr>
      <td>Słówka</td>
      <td><img src="https://github.com/user-attachments/assets/eba2372f-1042-4dae-a0a9-254b0f0fd964" alt="ekran_slowek" width="200"></td>
    </tr>
    <tr>
      <td>Słownik</td>
      <td><img src="https://github.com/user-attachments/assets/d43e30ba-4b6b-45d4-8e1b-3889c840838f" alt="ekran_slownika" width="200"></td>
    </tr>
  </tbody>
</table>

---

## Autorzy

- Piotr Woźniak
- Łukasz Sieradzki

---
