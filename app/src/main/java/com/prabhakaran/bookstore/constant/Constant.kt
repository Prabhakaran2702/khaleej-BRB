package com.prabhakaran.bookstore.constant

import com.prabhakaran.bookstore.model.Product
import java.util.*


object Constant {
    val QUANTITY_LIST: ArrayList<Int> = ArrayList()
    val PRODUCT1: Product = Product(
        1,
        "Harry Potter and the Order of the Phoenix",
        (49.00),
        "Dark times have come to Hogwarts. After the Dementors’ attack on his cousin Dudley, Harry Potter knows that Voldemort will stop at nothing to find him. There are many who deny the Dark Lord’s return, but Harry is not alone: a secret order gathers at Grimmauld Place to fight against the Dark forces. Harry must allow Professor Snape to teach him how to protect himself from Voldemort’s savage assaults on his mind. But they are growing stronger by the day and Harry is running out of time.",
        "https://brb.khaleejtimes.com/wp-content/uploads/2020/12/9781408855690-2.jpg",
        "200"
    )
    val PRODUCT2: Product = Product(
        2,
        "ONE ARRANGED MARRIAGE MURDER",
        (139.00),
        "Keshav has set up an investigation agency with his best friend, Saurabh. Can the two amateur detectives successfully solve another murder case that affects them personally? And where will it leave their friendship?\n" +
                "\n" +
                "\u0091Ever since you found Prerna, I lost my best friend\u0092 is what I told Saurabh.\n" +
                "Hi, this is Keshav, and Saurabh, my best friend, flatmate, colleague and business partner, won\u0092t talk to me. Because I made fun of him and his fiancée.",
        "https://brb.khaleejtimes.com/wp-content/uploads/2020/10/9781542094139-2.jpg",
        "670"
    )
    val PRODUCT3: Product = Product(
        3,
        "The Trees Told Me So",
        (45.00),
        "If trees could talk, they’d have so much to tell: The story of two broken hearts, that stolen first kiss, those last words of a mother to her son, endless cups of tea and never-ending chatter, of nostalgia, an act of brutality and a tale of passion. In this collection of short stories, The Trees Told Me So, Purva Grover draws a beautiful and poignant picture of love, life and loss, with an honest voice.",
        "https://brb.khaleejtimes.com/wp-content/uploads/2020/09/thetree.jpg",
        "2300"
    )

    val PRODUCT4: Product = Product(
        4,
        "DIARY OF A WIMPY KID: THE DEEP END",
        (49.00),
        "In The Deep End, book 15 of the Diary of a Wimpy Kid series from #1 international bestselling author Jeff Kinney, Greg Heffley and his family hit the road for a cross-country camping trip, ready for the adventure of a lifetime.",
        "https://brb.khaleejtimes.com/wp-content/uploads/2020/10/9780241424148-Cover.jpg",
        "400"
    )

    val PRODUCT5: Product = Product(
        5,
        "300+ SUCCESS BUSINESS LETTERPA",
        (60.00),
        " Business correspondence has an essential place in today’s business, financial, and marketing worlds, and this heavily revised and updated book shows how to set up correct formats for business letters of many different kinds, including letters of inquiry,",
        "https://brb.khaleejtimes.com/wp-content/uploads/2020/09/9780764143199-1.jpg",
        "340"
    )







    val PRODUCT6: Product = Product(
        6,
        "KERALA HISTORY AND ITS MAKERS",
        (23.00),
        "Pages from the history are full of biographies with eventful incidents. This book, by A Sreedhara Menon, provides some glimpses of the history of Kerala with focus on the accomplishments of select historical personages and groups of men who had a hand in shaping the course of Kerala History through the ages.",
        "https://brb.khaleejtimes.com/wp-content/uploads/2020/12/9788126421992.jpg",
        "100"
    )


    val PRODUCT7: Product = Product(
        6,
        "OLD MORTALITY",
        (20.00),
        "Old Morality is a historical novel based on the struggles of the Covenanters against the forces of both Scotland and England in seventeenth century Scotland. It traces the movement of the rebellion, from its victory at Drumclog to its ultimate defeat at Bothwell Bridge, through the eyes of its protagonist Henry Morton. Considered one of Scott",
        "https://brb.khaleejtimes.com/wp-content/uploads/2020/12/9788171674473.jpg",
        "80"
    )

    val PRODUCT8: Product = Product(
        8,
        "The Great Indian Novel",
        (50.00),
        "Writing in English, Shasi Tharoor has produced a stunning novel that is at once ambitious, daring, boldly satirical, and outrageously funny. The two structural pillars of his astonishing work are tha national epic, the Mahabharata, and the Indian struggle in this century for independence from Great Britain. “A masterpiece”–The Statesman.",
        "https://brb.khaleejtimes.com/wp-content/uploads/2020/12/9780143420088.jpg",
        "880"
    )

    val PRODUCT9: Product = Product(
        9,
        "THE ULTIMATE WINNING MINDS QUIZ CHALLENGE",
        (18.00),
        "Take on the ultimate weekly quiz challenge by India’s ace quizmaster. Is quizzing your passion? Do you want to answer new quizzes regularly? Are you looking for questions that will enhance and test your knowledge? Derek O’Brien has prepared the perfect quiz book for every kind of quizzer-beginner or advanced.",
        "https://brb.khaleejtimes.com/wp-content/uploads/2021/02/9788129142351.jpg",
        "880"
    )




    val PRODUCT10: Product = Product(
        10,
        "A BIOGRAPHY OF RAHUL DRAVID",
        (30.00),
        "The first Indian to score five double centuries in Test Cricket, each of those scores higher than the previous one; the first Indian to score centuries in four consecutive Test innings",
        "https://brb.khaleejtimes.com/wp-content/uploads/2020/09/9789353333119.jpg",
        "100"
    )


    val PRODUCT11: Product = Product(
        11,
        "A CENTURY IS NOT ENOUGH",
        (70.00),
        "iSourav Ganguly’s life has been full of highs and lows. Arguably India’s greatest cricket captain, he gave confidence to the team, re-energized them and took India, for the first time",
        "https://brb.khaleejtimes.com/wp-content/uploads/2020/09/9789386228567.jpg",
        "80"
    )

    val PRODUCT12: Product = Product(
        12,
        "THE DHONI TOUCH",
        (30.00),
        "For over a decade, Mahendra Singh Dhoni has captivated the world of cricket and over a billion Indians with his incredible ingenuity as captain, wicketkeeper and batsman. ",
        "https://brb.khaleejtimes.com/wp-content/uploads/2020/09/9780143440062.jpg",
        "880"
    )



    val PRODUCT_LIST: ArrayList<Product> = ArrayList<Product>()

    val PRODUCT_LIST_INDIAN: ArrayList<Product> = ArrayList<Product>()
    val PRODUCT_LIST_CRICKET: ArrayList<Product> = ArrayList<Product>()
    const val CURRENCY = "AED "

    init {
        for (i in 1..10) QUANTITY_LIST.add(i)
    }

    init {
        PRODUCT_LIST.add(PRODUCT1)
        PRODUCT_LIST.add(PRODUCT2)
        PRODUCT_LIST.add(PRODUCT3)
        PRODUCT_LIST.add(PRODUCT4)
        PRODUCT_LIST.add(PRODUCT5)


        PRODUCT_LIST_INDIAN.add(PRODUCT6)
        PRODUCT_LIST_INDIAN.add(PRODUCT7)
        PRODUCT_LIST_INDIAN.add(PRODUCT8)
        PRODUCT_LIST_INDIAN.add(PRODUCT9)
        PRODUCT_LIST_INDIAN.add(PRODUCT6)



        PRODUCT_LIST_CRICKET.add(PRODUCT10)
        PRODUCT_LIST_CRICKET.add(PRODUCT11)
        PRODUCT_LIST_CRICKET.add(PRODUCT12)
        PRODUCT_LIST_CRICKET.add(PRODUCT12)


    }
}
