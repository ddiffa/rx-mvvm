//package com.example.android.databinding.basicsample.util
//
//import com.example.android.databinding.basicsample.data.entity.MovieEntity
//import com.example.android.databinding.basicsample.data.entity.TvShowEntity
//
//fun generateDummyMovie(): ArrayList<MovieEntity> {
//
//    var movies = ArrayList<MovieEntity>()
//    movies.add(
//            MovieEntity(
//                    "169",
//                    "Horror,Comedy",
//                    "/zfE0R94v1E8cuKAerbskfD3VfUt.jpg",
//                    "/4W0FnjSGn4x0mKZlBRx8OjFxQUM.jpg",
//                    "It Chapter Two",
//                    7.2F,
//                    "27 years after overcoming the malevolent supernatural entity Pennywise, the former members of the Losers' Club, who have grown up and moved away from Derry, are brought back together by a devastating phone call.",
//                    "2019-09-06"
//            )
//    )
//
//    movies.add(
//            MovieEntity(
//                    107.toString(),
//                    "Comedy,Crime,Drama",
//                    "/y9IcwcD95wCPR3IGbzlZ00f64Sj.jpg",
//                    "/jTab4cf4X1dJJVS4F8UOGuesvPd.jpg",
//                    "Hustlers",
//                    6.8F,
//                    "A crew of savvy former strip club employees band together to turn the tables on their Wall Street clients.",
//                    "2019-09-12"
//            )
//    )
//    movies.add(
//            MovieEntity(
//                    126.toString(),
//                    "Action",
//                    "/fapXd3v9qTcNBTm39ZC4KUVQDNf.jpg",
//                    "/k2WyDw2NTUIWnuEs5gT7wgrCQg6.jpg",
//                    "Angel Has Fallen",
//                    5.6F,
//                    "Secret Service Agent Mike Banning is framed for the attempted assassination of the President and must evade his own agency and the FBI as he tries to uncover the real threat.",
//                    "2019-08-21"
//            )
//    )
//
//    movies.add(
//            MovieEntity(
//                    90.toString(),
//                    "Comedy",
//                    "/jIthqo2tQmW8TFN1fYpF8FmVL0o.jpg",
//                    "/6Xsz9KHQmCcIcj3CoWQq5eLtVoT.jpg",
//                    "Good Boys",
//                    6.6F,
//                    "A group of young boys on the cusp of becoming teenagers embark on an epic quest to fix their broken drone before their parents get home.",
//                    "2019-08-14"
//            )
//    )
//
//    movies.add(
//            MovieEntity(
//                    98.toString(),
//                    "Thriller,Crime,Mystery",
//                    "/vVYU0x9FRpiJNX7c54ciFnRBVYG.jpg",
//                    "/6rJAeP8xlq0bHUdCNg5epBvrFVa.jpg",
//                    "Night Hunter",
//                    6.2F,
//                    "A police task force traps an online predator, only to discover that the depth of his crimes goes far beyond anything they had anticipated.",
//                    "2019-08-29"
//            )
//    )
//
//    movies.add(
//            MovieEntity(
//                    118.toString(),
//                    "Adventure,Animation,Family,Drama",
//                    "/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg",
//                    "/1TUg5pO1VZ4B0Q1amk3OlXvlpXV.jpg",
//                    "The Lion King",
//                    7.2F,
//                    "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",
//                    "2019-07-12"
//            )
//    )
//
//    movies.add(
//            MovieEntity(
//                    136.toString(),
//                    "Action",
//                    "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg",
//                    "/hpgda6P9GutvdkDX5MUJ92QG9aj.jpg",
//                    "Fast & Furious Presents: Hobbs & Shaw",
//                    6.5F,
//                    "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                    "2019-08-01"
//            )
//    )
//
//    movies.add(
//            MovieEntity(
//                    119.toString(),
//                    "Thriller,Action",
//                    "/AlN758e1JphXDV9zeh6ZK3Um2KT.jpg",
//                    "/4XYxM4ZHX2mtdZe0z1psNUr7rxK.jpg",
//                    "Anna",
//                    6.6F,
//                    "Beneath Anna Poliatova's striking beauty lies a secret that will unleash her indelible strength and skill to become one of the world's most feared government assassins.",
//                    "2019-06-19"
//            )
//    )
//
//    movies.add(
//            MovieEntity(
//                    116.toString(),
//                    "Comedy,Music,Romance,Fantasy",
//                    "/7QPdjLr46huVv25W0eO2XZGOj3O.jpg",
//                    "/pWozCsrzMJzN8dbOm330sCKqdml.jpg",
//                    "Yesterday",
//                    6.8F,
//                    "Jack Malik is a struggling singer-songwriter in an English seaside town whose dreams of fame are rapidly fading, despite the fierce devotion and support of his childhood best friend, Ellie. After a freak bus accident during a mysterious global blackout, Jack wakes up to discover that he's the only person on Earth who can remember The Beatles.",
//                    "2019-06-27"
//            )
//    )
//
//    movies.add(
//            MovieEntity(
//                    90.toString(),
//                    "Horror, Science Fiction",
//                    "/rpS7ROczWulqfaXG2klYapULXKm.jpg",
//                    "/vHse4QK31Vc3X7BKKU5GOQhYxv6.jpg",
//                    "Child's Play",
//                    6.0F,
//                    "Karen, a single mother, gifts her son Andy a Buddi doll for his birthday, unaware of its more sinister nature. A contemporary re-imagining of the 1988 horror classic.",
//                    "2019-06-19"
//            )
//    )
//    return movies;
//
//}
//
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
//
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
//
