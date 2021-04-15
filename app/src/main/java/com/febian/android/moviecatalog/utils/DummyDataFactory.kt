package com.febian.android.moviecatalog.utils

import com.febian.android.moviecatalog.data.MovieEntity
import com.febian.android.moviecatalog.data.TvShowEntity

object DummyDataFactory {

    fun generateDummyMovies(): List<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "m1",
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "03/24/2021",
                "Action, Science Fiction",
                "8.3",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                "m2",
                "Zack Snyder's Justice League",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "03/18/2021",
                "Action, Adventure, Fantasy, Science Fiction",
                "8.5",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                "m3",
                "Chaos Walking",
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                "04/07/2021",
                "Science Fiction, Action, Adventure, Thriller",
                "7.4",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                "m4",
                "Raya and the Last Dragon",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "03/03/2021",
                "Animation, Adventure, Fantasy, Family, Action",
                "8.3",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                "m5",
                "Tom & Jerry",
                "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",
                "03/10/2021",
                "Comedy, Family, Animation",
                "7.3",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                "m6",
                "Secret Magic Control Agency",
                "The Secret Magic Control Agency sends its two best agents, Hansel and Gretel, to fight against the witch of the Gingerbread House.",
                "03/18/2021",
                "Animation, Comedy, Fantasy",
                "7.2",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4ZSzEDVdxWVMVO4oZDvoodQOEfr.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                "m7",
                "Below Zero",
                "When a prisoner transfer van is attacked, the cop in charge must fight those inside and outside while dealing with a silent foe: the icy temperatures.",
                "01/29/2021",
                "Action, Crime, Thriller",
                "6.4",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dWSnsAGTfc8U27bWsy2RfwZs0Bs.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                "m8",
                "Space Sweepers",
                "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
                "02/05/2021",
                "Drama, Fantasy, Science Fiction",
                "7.2",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/bmemsraCG1kIthY74NjDnnLRT2Q.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                "m9",
                "The Croods: A New Age",
                "Searching for a safer habitat, the prehistoric Crood family discovers an idyllic, walled-in paradise that meets all of its needs. Unfortunately, they must also learn to live with the Bettermans -- a family that's a couple of steps above the Croods on the evolutionary ladder. As tensions between the new neighbors start to rise, a new threat soon propels both clans on an epic adventure that forces them to embrace their differences, draw strength from one another, and survive together.",
                "12/23/2020",
                "Family, Fantasy, Animation, Comedy",
                "7.5",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tbVZ3Sq88dZaCANlUcewQuHQOaE.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                "m10",
                "Soul",
                "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                "12/25/2020",
                "Family, Animation, Comedy, Drama, Music, Fantasy",
                "8.3",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                false
            )
        )

        return movies
    }

    fun generateDummyTvShows(): List<TvShowEntity> {

        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(
                "t1",
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "03/19/2021",
                "Sci-Fi & Fantasy, Action & Adventure, Drama, War & Politics",
                "7.8",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                "t2",
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "09/25/2017",
                "Drama",
                "8.6",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                "t3",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "10/07/2014",
                "Drama, Sci-Fi & Fantasy",
                "7.7",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                "t4",
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "01/26/2017",
                "Mystery, Drama, Crime",
                "8.6",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                "t5",
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "03/27/2005",
                "Drama",
                "8.2",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                "t6",
                "Invincible",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "03/26/2021",
                "Animation, Action & Adventure, Drama",
                "8.9",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                "t7",
                "Who Killed Sara?",
                "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
                "03/24/2021",
                "Drama, Crime, Mystery",
                "7.8",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                "t8",
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "01/25/2016",
                "Crime, Sci-Fi & Fantasy",
                "8.5",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                "t9",
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "10/31/2010",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "8.1",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                "t10",
                "Superman & Lois",
                "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.",
                "02/23/2021",
                "Action & Adventure, Sci-Fi & Fantasy",
                "8.3",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6SJppowm7cdQgLkvoTlnTUSbjr9.jpg",
                false
            )
        )

        return tvShows
    }
}