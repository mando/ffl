# FFL

FFL - a service for taking URLs and, well, filing them for later

## CLI Commands

**Indexer**

Basic CLI for indexing urls:

```
$ ./script/index.sh http://www.mando.org | jq .
{
  "uri": "http://www.mando.org",
  "content": "unionmetrics :\nAnnouncing our new free Instagram account checkup & updated Instagram analytics dashboard!\nWe’ve got two huge pieces of news! First, we just launched our brand new free Instagram account checkup tool. Second, we’ve released a big update to our pro Instagram analytics. Read on for more about both.\nFree Instagram Account Checkup\nToday we released our brand new free Instagram account checkup! Just sign in and you’ll get a quick report with stats and insights about your Instagram activity. This is our first free Instagram analytics offering, and we’re really excited about it. With our new free report, you’ll be able to answer questions like:\nWhen’s the best time to post to Instagram?\nWho are your top fans?\nWhich hashtags get the most engagement?\nWhat kind of content should you post?\nGet your free Instagram account checkup now! You’ll have fresh, hot insights in just two minutes. \nUpdated Analytics Dashboard for Pro Subscribers\nWe’ve also just rolled out a big update for our paid Instagram analytics subscribers. Our new top-level dashboard includes an insight stream that automatically highlights key metrics and helps you decide exactly what to do next to improve engagement. It also includes at-a-glance Tracker comparisons and trend data across all accounts or hashtags you’re monitoring. The new additions will help you learn more about:\nWhat posts, videos, and hashtags are popular\nWho’s active or influential in a particular community\nHow one Instagram account’s performance compares to others\nImportant changes in metrics like follower growth and likes or comments\nExactly what to do next to improve post engagement\nIf you’re interested in trying our professional Instagram analytics out for yourself, plans start at just $99 per month and you can learn more here .\nPlease let us know if you have any questions or want to see a demo!\n",
  "id": "1c6da8fa16ed312bca2819fe2ebe9ee5005a284cccdd2ef6b9b4afb3c26ddebe44eac3ed429f9ad7c3196c5c6f91827ddc9b83813c36b2df1a884040a07d7edf"
}
```

**Extractor**

Basic CLI for showing the extracted content of a URL:

```
$ ./script/extract.sh http://www.mando.org
unionmetrics :
Announcing our new free Instagram account checkup & updated Instagram analytics dashboard!
We’ve got two huge pieces of news! First, we just launched our brand new free Instagram account checkup tool. Second, we’ve released a big update to our pro Instagram analytics. Read on for more about both.
Free Instagram Account Checkup
Today we released our brand new free Instagram account checkup! Just sign in and you’ll get a quick report with stats and insights about your Instagram activity. This is our first free Instagram analytics offering, and we’re really excited about it. With our new free report, you’ll be able to answer questions like:
When’s the best time to post to Instagram?
Who are your top fans?
Which hashtags get the most engagement?
What kind of content should you post?
Get your free Instagram account checkup now! You’ll have fresh, hot insights in just two minutes. 
Updated Analytics Dashboard for Pro Subscribers
We’ve also just rolled out a big update for our paid Instagram analytics subscribers. Our new top-level dashboard includes an insight stream that automatically highlights key metrics and helps you decide exactly what to do next to improve engagement. It also includes at-a-glance Tracker comparisons and trend data across all accounts or hashtags you’re monitoring. The new additions will help you learn more about:
What posts, videos, and hashtags are popular
Who’s active or influential in a particular community
How one Instagram account’s performance compares to others
Important changes in metrics like follower growth and likes or comments
Exactly what to do next to improve post engagement
If you’re interested in trying our professional Instagram analytics out for yourself, plans start at just $99 per month and you can learn more here .
Please let us know if you have any questions or want to see a demo!
```
## API

### Creating a document
**POST /document**

Parameters (represented as a json obj):

* _url_: the, well, URL (required)
* _filer_: name of person creating the document (optional)

Example:

    {
      "url": "http://github.com/mando/ffl",
      "filer": "mando"
    }

**Response**

A successful documention creation will return an `HTTP 201 Created` status code with a body similar to:

    {
      "id": "71362a20eaf519b1b62dd103ab1ab56c0a22b4610da750d60c617004a474485d",
      "url": "http://github.com/mando/ffl",
      "filed_time": 2016-01-01T00:00:00Z"
    }

Failures in document creation will result in one of the following return statuses:

* `HTTP 400 Bad Request` - malformed json bodies, missing required fields

### Getting a document
**GET /document/:id**

**Response**

    {
      "id": "71362a20eaf519b1b62dd103ab1ab56c0a22b4610da750d60c617004a474485d",
      "url": "http://github.com/mando/ffl",
      "filed_time": 2016-01-01T00:00:00Z"
    }

### Searching for a document
**GET /documents**

Query parameters:

* _q_: URL encoded search query (optional)
* _filer_: the person who filed the document (optional)
* _from_: ISO 8601 date time from which to start the search (optional)
* _to_: ISO 8601 date time which ends the search (optional)
* _order_: sort order (defaults to `revelancy`, other option is `date`)
* _num_: number of documents to return, default is 10 (optional)
* _page_: which page of paginated results to return, defaults to 1 (optional)

**Response**

    {
      "_meta": {
        "order": "relevancy",
        "total_match_count": 1000,
        "num_in_page": 10,
        "page_number": 1,
        "q": "mando is awesome"
      },
      "documents": [

      ]
    }
