package com.example.android.databinding.basicsample.util

import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.ResultsItem

fun generateDummyMovie(): ArrayList<ResultsItem> {

    var movies = ArrayList<ResultsItem>()
    movies.add(
            ResultsItem(
                    "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. Robotnik and his plans for world domination.",
                    "en",
                    "Sonic the Hedgehog",
                    false,
                    "Sonic the Hedgehog",
                    listOf(28,35,878,10751),
                    "/aQvJ5WPzZgYVDrxLX4R6cLJCEaQ.jpg",
                    "/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
                    "2020-02-12",
                    294.837,
                    7.2F,
                    169,
                    true,
                    862
            )
    )
    movies.add(
            ResultsItem(
                    "After her breakup with the Joker, Harley Quinn joins forces with singer Black Canary, assassin Huntress, and police detective Renee Montoya to help a young girl named Cassandra, who had a hit placed on her after she stole a rare diamond from crime lord Roman Sionis.",
                    "en",
                    "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",
                    false,
                    "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",
                    listOf(28,80,35),
                    "/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg",
                    "/2s1ofreBI8EFzIyL3SQrLRqO5Zx.jpg",
                    "2020-02-05",
                    205.76,
                    6.8F,
                    495764,
                    false,
                    1203
            )
    )
    movies.add(
            ResultsItem(
                    "After he and his wife are murdered, marine Ray Garrison is resurrected by a team of scientists. Enhanced with nanotechnology, he becomes a superhuman, biotech killing machine - Bloodshot. As Ray first trains with fellow super-soldiers, he cannot recall anything from his former life. But when his memories flood back and he remembers the man that killed both him and his wife, he breaks out of the facility to get revenge, only to discover that there's more to the conspiracy than he thought.",
                    "en",
                    "Bloodshot",
                    false,
                    "Bloodshot",
                    listOf(28,18,14,878),
                    "/8WUVHemHFH2ZIP6NWkwlHWsyrEL.jpg",
                    "/ocUrMYbdjknu2TwzMHKT9PBBQRw.jpg",
                    "2020-02-20",
                    143.374,
                    5.8F,
                    338762,
                    false,
                    70
            )
    )

    movies.add(
            ResultsItem(
                    "At the height of the First World War, two young British soldiers must cross enemy territory and deliver a message that will stop a deadly attack on hundreds of soldiers.",
                    "en",
                    "1917",
                    false,
                    "1917",
                    listOf(28,18,10752),
                    "/AuGiPiGMYMkSosOJ3BQjDEAiwtO.jpg",
                    "/cqa3sa4c4jevgnEJwq3CMF8UfTG.jpg",
                    "2019-12-25",
                    135.899,
                    8F,
                    530915,
                    false,
                    3132
            )
    )

    movies.add(
            ResultsItem(
                    "All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.",
                    "ko",
                    "기생충",
                    false,
                    "Parasite",
                    listOf(35,18,53),
                    "/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg",
                    "/TU9NIjwzjoKPwQHoHshkFcQUCG.jpg",
                    "2019-05-30",
                    104.288,
                    8.6F,
                    496243,
                    false,
                    5057
            )
    )
    movies.add(
            ResultsItem(
                    "When Cecilia's abusive ex takes his own life and leaves her his fortune, she suspects his death was a hoax. As a series of coincidences turn lethal, Cecilia works to prove that she is being hunted by someone nobody can see.",
                    "en",
                    "The Invisible Man",
                    false,
                    "The Invisible Man",
                    listOf(35,18,53),
                    "/4U7hpTK0XTQBKT5X60bKmJd05ha.jpg",
                    "/uZMZyvarQuXLRqf3xdpdMqzdtjb.jpg",
                    "2020-02-26",
                    114.654,
                    7.2F,
                    570670,
                    false,
                    364
            )
    )
    return movies;

}

//fun generateDummyTvShow(): ArrayList<TvShowEntity> {
//    var tvShows = ArrayList<TvShowEntity>()
//
//    tvShows.add(
//            TvShowEntity(
//                    "/dKxkwAJfGuznW8Hu0mhaDJtna0n.jpg",
//                    "2012-10-10",
//                    "Crime, Drama, Mystery, Action & Adventure",
//                    "Arrow",
//                    163,
//                    8,
//                    "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
//                    "/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
//                    5.8F
//            )
//    )
//
//    tvShows.add(
//            TvShowEntity(
//                    "/jC1KqsFx8ZyqJyQa2Ohi7xgL7XC.jpg",
//                    "2014-10-07",
//                    "Drama, Sci-Fi & Fantasy",
//                    "The Flash",
//                    116,
//                    6,
//                    "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
//                    "/fki3kBlwJzFp8QohL43g9ReV455.jpg", 6.7F
//            )
//    )
//
//    tvShows.add(
//            TvShowEntity(
//                    "/nUXzdD2Jo3wV9Q7mIZjK46Yyty4.jpg",
//                    "2015-08-23",
//                    "Drama, Horror",
//                    "Fear the Walking Dead",
//                    69,
//                    5,
//                    "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
//                    "/lZMb3R3e5vqukPbeDMeyYGf2ZNG.jpg",
//                    6.3F
//            )
//    )
//
//    tvShows.add(
//            TvShowEntity(
//                    "/bI37vIHSH7o4IVkq37P8cfxQGMx.jpg",
//                    "2019-07-25",
//                    "Sci-Fi & Fantasy, Action & Adventure",
//                    "The Boys",
//                    8,
//                    1,
//                    "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
//                    "/dzOxNbbz1liFzHU1IPvdgUR647b.jpg", 8.1F
//            )
//    )
//
//    tvShows.add(
//            TvShowEntity(
//                    "/o9OKe3M06QMLOzTl3l6GStYtnE9.jpg", "2005-09-13",
//                    "Drama, Mystery, Sci-Fi & Fantasy",
//                    "Supernatural",
//                    307,
//                    15,
//                    "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. ",
//                    "/3iFm6Kz7iYoFaEcj4fLyZHAmTQA.jpg", 7.3F
//            )
//    )
//
//    tvShows.add(
//            TvShowEntity(
//                    "/3OFrs1ets87VmRvG78Zg5eJTZeq.jpg",
//                    "1999-01-31",
//                    "Animation, Comedy",
//                    "Family Guy",
//                    329,
//                    18,
//                    "When the brewery announces it will be firing one employee, Peter is nervous he will be the one let go; the family departs on a Yacht Rock cruise that doesn't go as expected.",
//                    "/gBGUL1UTUNmdRQT8gA1LUV4yg39.jpg",
//                    6.5F
//            )
//    )
//
//    tvShows.add(
//            TvShowEntity(
//                    "/7DE9KC9GyY2TUJMnSPgYJG3rafw.jpg",
//                    "2018-10-12",
//                    "Action & Adventure, Sci-Fi, Drama",
//                    "Titans",
//                    14,
//                    2,
//                    "A team of young superheroes led by Nightwing (formerly Batman's first Robin) form to combat evil and other perils.",
//                    "/eeHI5iBSCOxj4HGSOmFM6azBmwb.jpg",
//                    7.4F
//            )
//    )
//
//    tvShows.add(
//            TvShowEntity(
//                    "/mKBP1OCgCG0jw8DwVYlnYqVILtc.jpg",
//                    "2014-09-22",
//                    "Drama, Fantasy, Crime",
//                    "Gothan",
//                    100,
//                    5,
//                    "Before there was Batman, there was GOTHAM. \n\nEveryone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker? ",
//                    "/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg",
//                    6.8F
//            )
//    )
//
//    tvShows.add(
//            TvShowEntity(
//                    "/1pP0gg0iNGX06wSs19EQOvzfZIF.jpg",
//                    "1989-12-17",
//                    "Animation, Comedy",
//                    "The Simpsons",
//                    663, 31,
//                    "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
//                    "/yTZQkSsxUFJZJe67IenRM0AEklc.jpg",
//                    7.1F
//            )
//    )
//
//    tvShows.add(
//            TvShowEntity(
//                    "/iWopHyAvuIDjGX10Yc3nn6UEebW.jpg",
//                    "2013-09-24",
//                    "Drama, Sci-Fi & Fantasy, Action & Adventure",
//                    "Marvel's Agents of S.H.I.E.L.D.",
//                    123,
//                    6,
//                    "With time running short, the team will have to go TO HELL AND BACK to stop the end of everything. Who will survive?",
//                    "/cXiETfFK1BTLest5fhTLfDLRdL6.jpg",
//                    6.8F
//            )
//    )
//    return tvShows
//}

//fun getMovie(movieName: String): MovieEntity? {
//    for (i in 0 until generateDummyMovie().size) {
//        val movie = generateDummyMovie()[i]
//        if (movie.title == movieName) return movie
//    }
//    return null
//}
//
//fun getTvShow(tvShowTitle: String): TvShowEntity? {
//    for (i in 0 until generateDummyTvShow().size) {
//        val tvShow = generateDummyTvShow()[i]
//        if (tvShow.titleTV == tvShowTitle) return tvShow
//    }
//    return null
//}

