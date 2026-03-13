package com.example.wildwaysenjoyer.data

import com.example.wildwaysenjoyer.model.ArtistInfo
import com.example.wildwaysenjoyer.model.Track
import com.example.wildwaysenjoyer.model.TrackStatus

// По ТЗ приложение полностью оффлайн, поэтому данные храним прямо в коде.
// val означает "неизменяемая ссылка": мы не хотим случайно переназначить этот объект.
val wildwaysArtistInfo = ArtistInfo(
    name = "Wildways",
    hometown = "Брянск",
    formedYear = "2009",
    genre = "Метал / альтернативный рок",
    about = "Российская металкор-группа, образованная в Брянске." +
            " Основана в сентябре 2009 года гитаристом Сергеем Новиковым." +
            " До 2014 года коллектив носил название «Sarah Where Is My Tea»." +
            " Артисты HS Music и VK Records."
)

// Это наш стартовый каталог: state holder берет его как исходную коллекцию.
// Именованные аргументы вроде title = ... делают длинные объекты заметно понятнее новичку.
val wildwaysTracks = listOf(
    Track(
        id = 1,
        title = "Breathless",
        album = "Day X",
        releaseYear = 2018,
        lyrics = "My calendar says I don't have enough time\n" +
                "I can't read my mind, no idea who am I\n" +
                "I've seen the people kill themselves\n" +
                "I've seen them lost inside their heads\n" +
                "This monster can grab anyone\n" +
                "Only the dream can help\n" +
                "\n" +
                "I run away for something more, I run away,\n" +
                "Please close the door\n" +
                "I don't need air when something takes my breath away\n" +
                "I'm still the kid I used to be,\n" +
                "Dreaming is right up my street\n" +
                "I don't need air when something takes my breath away\n" +
                "I don't need air when something takes my breath away\n" +
                "I don’t need air when something takes my breath away\n" +
                "\n" +
                "I gotta focus on the things really\n" +
                "Worth it\n" +
                "And now all pain is gone, I gotta count on myself\n" +
                "First day I couldn't believe it\n" +
                "Next week I had to admit it\n" +
                "Last couple days we've spent together\n" +
                "But nothing is forever\n" +
                "\n" +
                "I run away for something more, I run away,\n" +
                "Please close the door\n" +
                "I don't need air when something takes my breath away\n" +
                "I'm still the kid I used to be,\n" +
                "Dreaming is right up my street\n" +
                "I don't need air when something takes my breath away\n" +
                "I don't need air when something takes my breath away\n" +
                "I don’t need air when something takes my breath away\n" +
                "\n" +
                "Смотри мечтам в глаза, им есть что сказать\n" +
                "Им есть что сказать\n" +
                "Смотри мечтам в глаза, им есть что сказать\n" +
                "Смотри мечтам в глаза, им есть что сказать\n" +
                "Смотри мечтам в глаза, им есть что сказать\n" +
                "Смотри мечтам в глаза\n" +
                "\n" +
                "I run away for something more, I run away,\n" +
                "Please close the door\n" +
                "I don't need air when something takes my breath away\n" +
                "I'm still the kid I used to be,\n" +
                "Dreaming is right up my street\n" +
                "I don't need air when something takes my breath away\n" +
                "I don't need air when something takes my breath away\n",
        status = TrackStatus.FAVORITE
    ),
    Track(
        id = 2,
        title = "Comfort Zone",
        album = "Day X",
        releaseYear = 2018,
        lyrics = "My worst enemy is my memory\n" +
                "Memory, my memory\n" +
                "Why are you always calling me\n" +
                "Calling me, calling me?\n" +
                "Your voice is my favorite sound\n" +
                "I fell in love\n" +
                "Your voice is my favorite sound\n" +
                "I fell in love, I fell in love\n" +
                "\n" +
                "Don't mind me, I'm alone, alone\n" +
                "Don't mind me, I'm in my comfort zone\n" +
                "Don't mind me, I'm alone, alone\n" +
                "Don't mind me, I'm alone, alone\n" +
                "Don't mind me, I'm alone, alone\n" +
                "Don't mind me, I'm in my comfort zone\n" +
                "Don't mind me, I'm alone\n" +
                "Don’t mind me, I'm alone\n" +
                "\n" +
                "I'll come to you unless it's too late\n" +
                "Too late, is it too late?\n" +
                "I'll be on time so you don't have to wait\n" +
                "You don't have to wait, it's not too late\n" +
                "Everywhere we go, everybody's watching us\n" +
                "\"You belong together,\" everybody says to us\n" +
                "And I still don't know what is there to stop us?\n" +
                "What's there to stop us?\n" +
                "Your voice is my favorite sound\n" +
                "I fell in love, I fell in love\n" +
                "\n" +
                "Don't mind me, I'm alone, alone\n" +
                "Don't mind me, I'm in my comfort zone\n" +
                "Don't mind me, I'm alone, alone\n" +
                "Don't mind me, I'm alone, alone\n" +
                "Don't mind me, I'm alone, alone\n" +
                "Don't mind me, I'm in my comfort zone\n" +
                "Don't mind me, I'm alone\n" +
                "Don’t mind me, I'm alone\n" +
                "\n" +
                "Your voice is my favorite sound\n" +
                "I fell in love...\n",
        status = TrackStatus.LISTENED
    ),
    Track(
        id = 3,
        title = "Day X",
        album = "Day X",
        releaseYear = 2018,
        lyrics = "What if I could know?\n" +
                "What if I could know the day of the end?\n" +
                "What if I could change?\n" +
                "What if I could change the date?\n" +
                "\n" +
                "What the hell is going on?\n" +
                "It's just like a TV show\n" +
                "Everyone in the town\n" +
                "Running and running around\n" +
                "\n" +
                "Что если б знали мы числа? 5 или 7 или 30\n" +
                "Дней, чтобы закончить страницу, вот-вот, мы пыли частицы\n" +
                "Знаю, что осточертело в этом котле всем вариться\n" +
                "Лживые лица, кругом лишь лживые лица\n" +
                "Каждый день, как последний, сжигать мосты, как калории\n" +
                "И так горестно верить, конец целой истории\n" +
                "\n" +
                "What if I could know?\n" +
                "What if I could know the day of the end?\n" +
                "What if I could change?\n" +
                "What if I could change the date?\n" +
                "What if I could know?\n" +
                "What if I could know the day of the end?\n" +
                "What if I could change?\n" +
                "What if I could change the date?\n" +
                "What if I could know the day of the end?\n" +
                "What if I could change the date?\n" +
                "\n" +
                "So what if I could know?\n" +
                "So what if I could know?\n" +
                "So what if I could know the day?\n",
        status = TrackStatus.TO_LISTEN
    ),
    Track(
        id = 4,
        title = "Pazzle",
        album = "Day X",
        releaseYear = 2018,
        lyrics = "Woke up at five\n" +
                "Got the ticket in my pocket, now I go\n" +
                "I fly to hometown\n" +
                "I'm in the sky\n" +
                "21B, I'm traveling alone\n" +
                "Looking in the window\n" +
                "\n" +
                "I'm picking up the puzzle all night\n" +
                "Your ghost inside of my house, all white\n" +
                "And now you see me, I'm alright\n" +
                "I'm picking up, I'm picking up\n" +
                "The pieces of my broken heart\n" +
                "\n" +
                "I'm picking up the puzzle all night\n" +
                "Your ghost inside of my house, all white\n" +
                "And now you see me, I'm alright\n" +
                "I'm picking up, I'm picking up\n" +
                "I know you're not happy staying here\n" +
                "And just to see me is your fear\n" +
                "You're picking up the pieces of us\n" +
                "Мы живём в ожидании, когда настанет тот час\n" +
                "\n" +
                "Open the door\n" +
                "I could live life for you, could you live for me?\n" +
                "Live your life for me\n" +
                "These walls can speak\n" +
                "The walls have seen how our lives turned to grey\n" +
                "Our home turned to waste\n" +
                "Two more days\n" +
                "I can't imagine I've been here so many years\n" +
                "I fly away, would you fly with me?\n" +
                "\n" +
                "I'm picking up the puzzle all night\n" +
                "Your ghost inside of my house, all white\n" +
                "And now you see me, I'm alright\n" +
                "I'm picking up, I'm picking up\n" +
                "I know you're not happy staying here\n" +
                "And just to see me is your fear\n" +
                "You're picking up the pieces of us\n" +
                "Мы живём в ожидании, когда настанет тот час\n" +
                "\n" +
                "Некогда жилой дом стал пустой\n" +
                "Лишь фантом обитает в нём твой\n" +
                "Родной город стал совсем чужой\n" +
                "И тебе чужой, я стал тебе чужой\n",
        status = TrackStatus.LISTENED
    ),
    Track(
        id = 5,
        title = "Lost",
        album = "Day X",
        releaseYear = 2018,
        lyrics = "There are two parts of me, so now it’s like a war\n" +
                "I’ve never seen God to ask him what I’m looking for\n" +
                "I’ve never seen the rules\n" +
                "I try to play this game\n" +
                "So what is your name? I’m meeting doomsday\n" +
                "But I still don’t know to whom do I pray\n" +
                "This can never reform mankind ’cause this is slavery\n" +
                "You can’t answer what you try to find\n" +
                "It’s tragedy\n" +
                "Where have you been when I needed to breathe?\n" +
                "So where have you been when I needed to breathe?\n" +
                "\n" +
                "I lost my faith somewhere inside, inside\n" +
                "I lose myself somewhere in faith, in faith\n" +
                "\n" +
                "I came all this way for you\n" +
                "But I still don’t know how you look\n" +
                "And I still don’t know what I choose\n" +
                "Is that truth give me light? Is that truth?\n" +
                "I’ve never heard any echo\n" +
                "They’re all gone, they’re all gone\n" +
                "There is no hell, there is no heaven\n" +
                "There is no life on the other side\n" +
                "There is no hell, there is no heaven\n" +
                "There’s no life on the other side\n" +
                "\n" +
                "I lost my faith somewhere inside, inside\n" +
                "I lose myself somewhere in faith, in faith, in faith\n" +
                "\n" +
                "I lost my faith somewhere inside, inside\n" +
                "I lose myself somewhere in faith, in faith\n",
        status = TrackStatus.FAVORITE
    ),
    Track(
        id = 6,
        title = "Километры",
        album = "Километры",
        releaseYear = 2019,
        lyrics = "Снова холод сотен километров\n" +
                "Задержи дыхание, задержи\n" +
                "\n" +
                "Все равно вернусь\n" +
                "Хоть и мне грустно так часто\n" +
                "Все города наизусть\n" +
                "Они уверенно думают, что я счастлив\n" +
                "Ну и пусть, кричать, еще один темный зал\n" +
                "Во встречном свете фонарей я ищу твои глаза\n" +
                "\n" +
                "Снова холод сотен километров\n" +
                "Задержи дыхание, задержи\n" +
                "Дома было бы намного легче\n" +
                "Но за столом я один\n" +
                "\n" +
                "23 этаж, будто новый рассказ\n" +
                "Везде ветер и шаг ему навстречу, чтоб сбежать\n" +
                "С чайками и меж облаков\n" +
                "Карамельным закатом балтийских вод\n" +
                "Накроет печаль, но без слез\n" +
                "Ведь я привык давно\n" +
                "\n" +
                "Снова холод сотен километров\n" +
                "Задержи дыхание, задержи\n" +
                "Дома было бы намного легче\n" +
                "Но за столом я один\n" +
                "\n" +
                "Я так и не смог свое сердце отдать\n" +
                "В нужные руки\n" +
                "Я так и не смог свое сердце отдать\n" +
                "В нужные руки, в нежные руки\n" +
                "\n" +
                "Снова холод сотен километров\n" +
                "Задержи дыхание, задержи\n" +
                "Дома было бы намного легче\n" +
                "Но за столом я один\n" +
                "\n" +
                "Я так и не смог свое сердце отдать\n" +
                "Я так и не смог свое сердце отдать\n" +
                "Я один\n",
        status = TrackStatus.TO_LISTEN
    )
)
