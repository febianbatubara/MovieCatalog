package com.febian.android.moviecatalog.utils

import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity

object DummyDataFactory {

    fun generateDummyMovies(): List<MovieEntity> {

        val movies = ArrayList<MovieEntity>()
        movies.add(
            MovieEntity(
                460465,
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2021-04-07",
                "Tagline",
                8f,
                0,
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                2,
                "Zack Snyder's Justice League",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "03/18/2021",
                "Tagline",
                8f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "https://image.tmdb.org/t/p/w780/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                3,
                "Chaos Walking",
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                "04/07/2021",
                "Tagline",
                8f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "https://image.tmdb.org/t/p/w780/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                4,
                "Raya and the Last Dragon",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "03/03/2021",
                "Tagline",
                8.3f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "https://image.tmdb.org/t/p/w780/7prYzufdIOy1KCTZKVWpjBFqqNr.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                5,
                "Tom & Jerry",
                "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",
                "03/10/2021",
                "Tagline",
                7.3f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                "https://image.tmdb.org/t/p/w780/fev8UFNFFYsD5q7AcYS8LyTzqwl.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                6,
                "Secret Magic Control Agency",
                "The Secret Magic Control Agency sends its two best agents, Hansel and Gretel, to fight against the witch of the Gingerbread House.",
                "03/18/2021",
                "Tagline",
                7.2f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4ZSzEDVdxWVMVO4oZDvoodQOEfr.jpg",
                "https://image.tmdb.org/t/p/w780/9Is9OrQUnKczCfsLSbsbx8YSmES.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                7,
                "Below Zero",
                "When a prisoner transfer van is attacked, the cop in charge must fight those inside and outside while dealing with a silent foe: the icy temperatures.",
                "01/29/2021",
                "Tagline",
                6.4f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dWSnsAGTfc8U27bWsy2RfwZs0Bs.jpg",
                "https://image.tmdb.org/t/p/w780/6TPZSJ06OEXeelx1U1VIAt0j9Ry.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                8,
                "Space Sweepers",
                "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
                "02/05/2021",
                "Tagline",
                7.2f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/bmemsraCG1kIthY74NjDnnLRT2Q.jpg",
                "https://image.tmdb.org/t/p/w780/drulhSX7P5TQlEMQZ3JoXKSDEfz.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                9,
                "The Croods: A New Age",
                "Searching for a safer habitat, the prehistoric Crood family discovers an idyllic, walled-in paradise that meets all of its needs. Unfortunately, they must also learn to live with the Bettermans -- a family that's a couple of steps above the Croods on the evolutionary ladder. As tensions between the new neighbors start to rise, a new threat soon propels both clans on an epic adventure that forces them to embrace their differences, draw strength from one another, and survive together.",
                "12/23/2020",
                "Tagline",
                7.5f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tbVZ3Sq88dZaCANlUcewQuHQOaE.jpg",
                "https://image.tmdb.org/t/p/w780/cjaOSjsjV6cl3uXdJqimktT880L.jpg",
                false
            )
        )
        movies.add(
            MovieEntity(
                10,
                "Soul",
                "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                "12/25/2020",
                "Tagline",
                8.3f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                "https://image.tmdb.org/t/p/w780/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                false
            )
        )

        return movies
    }

    fun generateDummyTvShows(): List<TvShowEntity> {

        val tvShows = ArrayList<TvShowEntity>()
        tvShows.add(
            TvShowEntity(
                88396,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2021-03-19",
                "Tagline",
                7.9f,
                0,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                12,
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "09/25/2017",
                "Tagline",
                8.6f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "https://image.tmdb.org/t/p/w780/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                13,
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "10/07/2014",
                "Tagline",
                7.3f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "https://image.tmdb.org/t/p/w780/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                14,
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "01/26/2017",
                "Tagline",
                8.6f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                "https://image.tmdb.org/t/p/w780/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                15,
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "03/27/2005",
                "Tagline",
                8.2f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "https://image.tmdb.org/t/p/w780/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                16,
                "Invincible",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "03/26/2021",
                "Tagline",
                8.9f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "https://image.tmdb.org/t/p/w780/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                17,
                "Who Killed Sara?",
                "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
                "03/24/2021",
                "Tagline",
                7.8f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg",
                "https://image.tmdb.org/t/p/w780/pLG4ihU1d2XkQbASQDjsFu9U7d9.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                18,
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "01/25/2016",
                "Tagline",
                8.5f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "https://image.tmdb.org/t/p/w780/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                19,
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "10/31/2010",
                "Tagline",
                8.1f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                "https://image.tmdb.org/t/p/w780/uro2Khv7JxlzXtLb8tCIbRhkb9E.jpg",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                20,
                "Superman & Lois",
                "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.",
                "02/23/2021",
                "Tagline",
                8.3f,
                0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6SJppowm7cdQgLkvoTlnTUSbjr9.jpg",
                "https://image.tmdb.org/t/p/w780/gmbsR4SvYhhj4SvLAlTKxIkFxp9.jpg",
                false
            )
        )

        return tvShows
    }
}