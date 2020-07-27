package com.example.chatapp

class QuoteArray {
    companion object {
        //"inspiration","love","sadness","motivation","determination","poetry","team","sports"
        lateinit var inspiration: ArrayList<String>
        fun inspirationArray(): ArrayList<String> {
            inspiration = ArrayList<String>()
            inspiration.add("Inspiration is wonderful when it happens, but the writer must develop an approach for the rest of the time ")
            inspiration.add("The inspiration of the almighty gives man understanding. ")
            inspiration.add("Wisdom is supreme; therefore make a full effort to get wisdom. Esteem her and she will exalt you; embrace her and she will honor you. ")
            inspiration.add("When you do not know what you are doing and what you are doing is the best -- that is inspiration. ")
            inspiration.add("There never was a great soul that did not have some divine inspiration. ")
            return inspiration
        }

        lateinit var love: ArrayList<String>
        fun loveArray(): ArrayList<String> {
            love = ArrayList<String>()
            love.add("He who is not impatient is not in love. ")
            love.add("Who travels for love finds a thousand miles not longer than one. ")
            love.add("A man in love mistakes a pimple for a dimple. ")
            love.add("It is easier to guard a sack full of fleas than a girl in love. ")
            love.add("He that plants trees loves others besides himself. ")
            return love
        }

        lateinit var sadness: ArrayList<String>
        fun sadnessArray(): ArrayList<String> {
            sadness = ArrayList<String>()
            sadness.add("The way sadness works is one of the strange riddles of the world. If you are stricken with a great sadness, you may feel as if you have been set aflame, not only because of the enormous pain but also because your sadness may spread over your life, like smoke from an enormous fire.")
            sadness.add("“Our sweetest songs are those that tell of saddest thought.”")
            sadness.add("“Sadness flies away on the wings of time.”")
            sadness.add("“Sadness is but a wall between two gardens.”")
            sadness.add("“Tears are words that need to be written.”")
            return sadness
        }

        lateinit var motivation: ArrayList<String>
        fun motivationArray(): ArrayList<String> {
            motivation = ArrayList<String>()
            motivation.add("Our lives are not determined by what happens to us but by how we react to what happens, not by what life brings to us, but by the attitude we bring to life. A positive attitude causes a chain reaction of positive thoughts, events, and outcomes. It is a catalyst, a spark that creates extraordinary results. ")
            motivation.add("All your life you are told the things you cannot do. All your life they will say you're not good enough or strong enough or talented enough; they will say you're the wrong height or the wrong weight or the wrong type to play this or be this or achieve this. THEY WILL TELL YOU NO, a thousand times no, until all the no's become meaningless. All your life they will tell you no, quite firmly and very quickly.\n" +
                    "AND YOU WILL TELL THEM YES. ")
            motivation.add("It is not the horse that draws the cart, but the oats. ")
            motivation.add("Men are more accountable for their motives, than for anything else; and primarily, morality consists in the motives, that is in the affections.")
            motivation.add("God made man to go by motives, and he will not go without them, any more than a boat without steam or a balloon without gas. ")
            return motivation
        }

        lateinit var determination: ArrayList<String>
        fun determinationArray(): ArrayList<String> {
            determination = ArrayList<String>()
            determination.add("To him who is determined it remains only to act. ")
            determination.add("The mighty Oak was once a little nut that stood its ground. ")
            determination.add("Don't tell me the sky's the limit when there are foosteps on the moon. ")
            determination.add("I'll have a full recovery - that's the utmost physically my body has the ability to heal. Then I will push about 20 percent further, through sheer mental tenacity. If you're not prepared for that, go elsewhere. ")
            determination.add("The manner in which one endures what must be endured is more important than the thing that must be endured. ")
            return determination
        }

        lateinit var poetry: ArrayList<String>
        fun poetryArray(): ArrayList<String> {
            poetry = ArrayList<String>()
            poetry.add("A poet is someone who is astonished by everything. ")
            poetry.add("I don't see how poetry can ever be easy... Real poetry, the thick, dense, intense, complicated stuff that lives and endures, requires blood sweat; blood and sweat are essential elements in poetry as well as behind it. ")
            poetry.add("A poem records emotions and moods that lie beyond normal language, that can only be patched together and hinted at metaphorically. ")
            poetry.add("Poetry is finer and more philosophical than history; for poetry expresses the universal, and history only the particular. ")
            poetry.add("It takes most men five years to recover from a college education, and to learn that poetry is as vital to thinking as knowledge. ")
            return poetry
        }

        lateinit var team: ArrayList<String>
        fun teamArray(): ArrayList<String> {
            team = ArrayList<String>()
            team.add("No problem is insurmountable. With a little courage, teamwork and determination a person can overcome anything. ")
            team.add("Teamwork is essential. It allows you to blame someone else. ")
            team.add("Two heads are better than one. ")
            team.add("When spider webs unite, they can tie up a lion. ")
            team.add("One man alone can be pretty dumb sometimes, but for real bona fide stupidity, there ain't nothin' can beat teamwork. ")
            return team
        }

        lateinit var sports: ArrayList<String>
        fun sportsArray(): ArrayList<String> {
            sports = ArrayList<String>()
            sports.add("Winners never quit and quitters never win. ")
            sports.add("Since the Bible and the church are obviously mistaken in telling us where we came from, how can we trust them to tell us where we are going? ")
            sports.add("I strongly believe the black culture spends too much time, energy and effort raising, praising, and teasing our black children about the dubious glories of professional sports. ")
            sports.add("There is a syndrome in sports called paralysis by analysis. ")
            sports.add("For man, maximum excitement is the confrontation of death and the skillful defiance of it by watching others fed to it as he survives transfixed with rapture. ")
            return sports
        }

        fun getArrayList(category: String): ArrayList<String> {
            if (category.equals("inspiration"))
                return inspirationArray()
            else if (category.equals("love"))
                return loveArray()
            else if (category.equals("sadness"))
                return sadnessArray()
            else if (category.equals("motivation"))
                return motivationArray()
            else if (category.equals("determination"))
                return determinationArray()
            else if (category.equals("poetry"))
                return poetryArray()
            else if (category.equals("team"))
                return teamArray()
            else if (category.equals("sports"))
                return sportsArray()
            else {
                val failedArray: ArrayList<String>
                failedArray = ArrayList<String>()
                return failedArray
            }
        }

    }
}