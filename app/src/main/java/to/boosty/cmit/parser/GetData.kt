package to.boosty.cmit.parser

import org.jsoup.Jsoup
import to.boosty.cmit.parser.lists.Receipts

class GetData {

    private val listReceipts = mutableListOf<Receipts>()
    private val url = "https://www.edimdoma.ru/retsepty"


    fun jsoupScan(): MutableList<Receipts> {


        val document = Jsoup.connect(url).get()
        val element = document.select("article[class = card]")

        for (i in 0 until element.size) {
            val title = element.select("div[class = card__title title]")
                .eq(i)
                .text()

            val desc = element.select("div[class = card__description]")
                .select("p")
                .eq(i)
                .text()

            /*val linkImage = element.select("article[class = card]")
                    .select("img")
                    .eq(i)
                    .attr("src")*/

                val addInfo = element.select("div[class = person__name]")
                    .eq(i)
                    .text()

            listReceipts.add(Receipts(title, desc, addInfo))//, linkImage, addInfo))
        }
        return listReceipts
    }
}

