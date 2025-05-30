
# EnglishBee

Aplikacja do nauki języka angielskiego w języku Kotlin, korzystająca z Words.Api oraz lokalnej bazy danych Room


## Funkcjonalności

- Menu wybory formy nauki
- Quiz gramatyczny
- Odmiana czasowników nieregularnych
- Tłumaczenie słówek
- Wyszukiwanie znacznenia słów w słówniku

## Tech Stack

- **Język programowania:** Kotlin
- **Framework:** Jetpack Compose
- **Baza danych:** Room
- **Front-end:**
- **Back-end:** Words.Api

## API

#### Get all items

```http
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
  GET /api/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |



## Zrzuty Ekranu
![ekran_ladowania](https://github.com/user-attachments/assets/25d20f21-c39f-4752-b2e3-3de2303f0700)
![ekran_startowy](https://github.com/user-attachments/assets/780505a1-7183-4a8d-9ed6-b968f016a709)
![ekran_menu](https://github.com/user-attachments/assets/8a8a54c4-972f-4783-86fd-ed76be51bdf3)
![ekran_gramatyki](https://github.com/user-attachments/assets/be727f34-306c-4d4a-8686-26c984a20f8b)
![ekran_czasownikow](https://github.com/user-attachments/assets/59e00ecd-aed5-4b81-93da-7d29149088a3)
![ekran_slowek](https://github.com/user-attachments/assets/7e1e3e3f-5838-45a8-a7ef-7762f080e9ce)
![ekran_slownika](https://github.com/user-attachments/assets/d43e30ba-4b6b-45d4-8e1b-3889c840838f)



## Autorzy

- Piotr Woźniak
- Łukasz Sieradzki

