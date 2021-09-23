-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 17 Gru 2020, 18:22
-- Wersja serwera: 10.4.14-MariaDB
-- Wersja PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `aplikacja`
--
CREATE DATABASE IF NOT EXISTS `aplikacja` DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;
USE `aplikacja`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `category`
--

CREATE TABLE `category` (
  `Id` int(11) NOT NULL,
  `nazwa` varchar(64) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `category`
--

INSERT INTO `category` (`Id`, `nazwa`) VALUES
(1, 'Figurki'),
(2, 'Przypinki'),
(3, 'Naklejki');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `orders`
--

CREATE TABLE `orders` (
  `Id` int(11) NOT NULL,
  `Id_user` int(11) NOT NULL,
  `Id_shipment` int(11) NOT NULL,
  `dostawa_informacje` varchar(128) COLLATE utf8_polish_ci DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `informacje_dodatkowe` varchar(256) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `orders_list`
--

CREATE TABLE `orders_list` (
  `Id` int(11) NOT NULL,
  `Id_order` int(11) NOT NULL,
  `Id_product` int(11) NOT NULL,
  `ilosc` int(11) NOT NULL,
  `cena_sztuka` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `products`
--

CREATE TABLE `products` (
  `Id` int(11) NOT NULL,
  `nazwa` varchar(128) COLLATE utf8_polish_ci NOT NULL,
  `id_category` int(11) NOT NULL,
  `anime` varchar(128) COLLATE utf8_polish_ci NOT NULL,
  `wielkosc` varchar(32) COLLATE utf8_polish_ci NOT NULL,
  `producent` varchar(64) COLLATE utf8_polish_ci NOT NULL,
  `cena` decimal(10,2) NOT NULL,
  `opis` text COLLATE utf8_polish_ci NOT NULL,
  `media` varchar(128) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `products`
--

INSERT INTO `products` (`Id`, `nazwa`, `id_category`, `anime`, `wielkosc`, `producent`, `cena`, `opis`, `media`) VALUES
(1, 'Megumin Nendoroid', 1, 'Kono Subarashii Sekai ni Shukufuku wo! (KONOSUBA)', '10cm', 'Good Smile Company', '50.99', 'Ponowne wydanie Nendoroida największego arcymaga zaklęcia eksplozji - Megumin! Jest wyposażona w trzy osłony twarzy, w tym uśmiechniętą twarz, poważną twarz do rzucania zaklęć, a także słodki, nieśmiały wyraz twarzy.\r\n<br><br>\r\nJej charakterystyczny kapelusz czarodzieja można łatwo założyć i zdjąć w celu szybkiej zmiany wyglądu, a także różne inne opcjonalne części, takie jak laska, część z efektem wybuchu, modna opaska na oko i błyszczące części z efektem magii!', 'Megumin_Nendoroid'),
(2, 'Megumin w mundurku szkolnym', 1, 'Kono Subarashii Sekai ni Shukufuku wo! (KONOSUBA)', '24cm', 'Chara-Ani', '149.95', 'Arcymag z Klanu Karmazynowych Demonów Megumin w mundurze z czasów, gdy uczęszczała do szkoły w Szkarłatnej Wiosce Demonów! \r\n<br><br>\r\nJej płaszcz powiewający na wietrze i mundur zostały wykonane w najdrobniejszych szczegółach. \r\n<br>\r\nMegumin ma pewny wyraz twarzy, gdy przyjmuje pozę. \r\n<br><br>\r\nPamiętaj, aby dodać ją do swojej kolekcji!', 'Megumin_School_Uniform'),
(3, 'Re:Zero | 50 unikalnych', 3, 'Re:Zero kara Hajimeru Isekai Seikatsu', '50 sztuk', 'Anime Shop', '9.99', '50 niepowtarzających się wysokiej jakości naklejek!<br>\r\n<br>\r\nPojedyńcza naklejka ma rozmiar ~5 cm.<br>\r\nWytrzymały materiał odporny na zarysowania (winyl), w 100% wodoodporny, nie zmieniający koloru.<br>\r\nDoskonałe na telefon, laptop, walizkę, lodówkę, szafę, samochód, motocykl, rower i wiele innych!', 'ReZero_50'),
(4, 'Darling in the FranXX | 50 unikalnych', 3, 'Darling in the FranXX', '50 sztuk', 'Anime Shop', '9.99', '50 niepowtarzających się wysokiej jakości naklejek!<br>\r\n<br>\r\nPojedyńcza naklejka ma rozmiar ~4 cm.<br>\r\nWytrzymały materiał odporny na zarysowania (winyl), w 100% wodoodporny, nie zmieniający koloru.<br>\r\nDoskonałe na telefon, laptop, walizkę, lodówkę, szafę, samochód, motocykl, rower i wiele innych!', 'Darling_Franxx_50'),
(5, 'Genshin Impact | 50 unikalnych', 3, 'Genshin Impact', '50 sztuk', 'Anime Shop', '9.99', '50 niepowtarzających się wysokiej jakości naklejek!<br>\r\n<br>\r\nPojedyńcza naklejka ma rozmiar ~5 cm.<br>\r\nWytrzymały materiał odporny na zarysowania (winyl), w 100% wodoodporny, nie zmieniający koloru.<br>\r\nDoskonałe na telefon, laptop, walizkę, lodówkę, szafę, samochód, motocykl, rower i wiele innych!', 'Genshin_Impact_50'),
(6, 'Emilia', 2, 'Re:Zero kara Hajimeru Isekai Seikatsu', '5.8cm', 'Anime Shop', '4.99', 'Duża, wytrzymała przypinka wysokiej jakości z wizerunkiem Emilii.', 'Emilia_pin'),
(9, 'Kurumi', 2, 'Date A Live', '5.8cm', 'Anime Shop', '4.99', 'Duża, wytrzymała przypinka wysokiej jakości z wizerunkiem Kurumi.', 'Kurumi_pin'),
(10, 'Aqua POP UP PARADE', 1, 'Kono Subarashii Sekai ni Shukufuku wo! (KONOSUBA)', '18.5cm', 'Good Smile Company', '114.45', 'Wspaniała bogini Aqua z serii POP UP PARADE!<br>\r\n<br>\r\nJej energiczna osobowość z anime została starannie zachowana w formie figurki.', 'Aqua_Pop_Up_Parade');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `shipments`
--

CREATE TABLE `shipments` (
  `Id` int(11) NOT NULL,
  `nazwa` varchar(128) COLLATE utf8_polish_ci NOT NULL,
  `koszt` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `shipments`
--

INSERT INTO `shipments` (`Id`, `nazwa`, `koszt`) VALUES
(3, 'Paczkomat InPost', '9.99'),
(6, 'Kurier InPost', '19.99'),
(7, 'Kurier DHL', '24.49');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `Id` int(11) NOT NULL,
  `email` varchar(128) COLLATE utf8_polish_ci NOT NULL,
  `password` varchar(128) COLLATE utf8_polish_ci NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT 0,
  `imie` varchar(64) COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` varchar(64) COLLATE utf8_polish_ci NOT NULL,
  `telefon` varchar(12) COLLATE utf8_polish_ci NOT NULL,
  `kod_pocztowy` varchar(6) COLLATE utf8_polish_ci NOT NULL,
  `miasto` varchar(64) COLLATE utf8_polish_ci NOT NULL,
  `ulica` varchar(64) COLLATE utf8_polish_ci NOT NULL,
  `nr_domu` varchar(16) COLLATE utf8_polish_ci NOT NULL,
  `nr_lokalu` varchar(8) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`Id`, `email`, `password`, `admin`, `imie`, `nazwisko`, `telefon`, `kod_pocztowy`, `miasto`, `ulica`, `nr_domu`, `nr_lokalu`) VALUES
(6, 'admin@shop.pl', 'bc077426e3d5d0c5572b062e2c19a08d171e1846f003e0d2168be38a90763899', 1, 'Arkadiusz', 'Nowak', '123321123', '97-200', 'Tomaszów Mazowiecki', 'Warszawska', '13', '2C'),
(7, 'JanKowalski@gmail.com', '30d37c4470ff0f2a913d6600101c4035aa83b60b626e88d545a66d38d08ff003', 0, 'Jan', 'Kowalski', '519465781', '00-055', 'Warszawa', 'Polna', '41', NULL);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`Id`);

--
-- Indeksy dla tabeli `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `order_user` (`Id_user`),
  ADD KEY `order_shipment` (`Id_shipment`);

--
-- Indeksy dla tabeli `orders_list`
--
ALTER TABLE `orders_list`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `orderlist_order` (`Id_order`),
  ADD KEY `orderlist_product` (`Id_product`);

--
-- Indeksy dla tabeli `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `products_category` (`id_category`);

--
-- Indeksy dla tabeli `shipments`
--
ALTER TABLE `shipments`
  ADD PRIMARY KEY (`Id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `category`
--
ALTER TABLE `category`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `orders`
--
ALTER TABLE `orders`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `orders_list`
--
ALTER TABLE `orders_list`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `products`
--
ALTER TABLE `products`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT dla tabeli `shipments`
--
ALTER TABLE `shipments`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `order_shipment` FOREIGN KEY (`Id_shipment`) REFERENCES `shipments` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `order_user` FOREIGN KEY (`Id_user`) REFERENCES `users` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli `orders_list`
--
ALTER TABLE `orders_list`
  ADD CONSTRAINT `orderlist_order` FOREIGN KEY (`Id_order`) REFERENCES `orders` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `orderlist_product` FOREIGN KEY (`Id_product`) REFERENCES `products` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
