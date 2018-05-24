import tmdbsimple as tmdb
import time
import json
import pymysql
tmdb.API_KEY = 'API-KEY'

with open("TVList.txt", 'r') as f:
    data = f.read().split('\n')
db= pymysql.connect("localhost","ripepotatoes","changeit","ripepotatoes")

try:
    with db.cursor() as cursor:
        db.set_charset('utf8')
        cursor.execute('SET NAMES utf8;')
        cursor.execute('SET CHARACTER SET utf8;')
        cursor.execute('SET character_set_connection=utf8;')
        for name in data:
        # insert a single movie
            search = tmdb.Search()
            tv_title = name
            search.tv(query=tv_title)
            tv_id = search.results[0]['id']
            tv = tmdb.TV(tv_id)
            tv.info()
            sql="INSERT INTO content (contentid,is_featured,name,num_rating,sum_rating) VALUES(%s,%s,%s,%s,%s )"
            year=tv.first_air_date[:4]
            sum_rating = int(tv.vote_average * tv.vote_count)
            rating = int(tv.vote_average)
            cursor.execute(sql, (tv.id, 0, tv.name, rating,sum_rating))
            sql = "INSERT INTO tvseries (network,contentid) VALUES(%s,%s)"
            cursor.execute(sql,(tv.networks[0].get("name"),tv.id))
            sql = "INSERT INTO media(critic_rating,release_date,synopsis,year,contentid) VALUES (%s, %s, %s, %s,%s)"
            cursor.execute(sql,(0,tv.first_air_date,tv.overview[:255],year,tv.id))
            for season in tv.seasons:
                time.sleep(10)
                print(season)
                sql = "INSERT INTO tvseries_seasons (tvseries_contentid,seasons_season_id) VALUES(%s,%s)"
                cursor.execute(sql, (tv.id,season.get("id")))
                sql = "INSERT INTO season (synopsis,tv_id,season_id) VALUES(%s,%s,%s)"
                cursor.execute(sql,(season.get("overview")[:255],tv.id,season.get("id")))
                i=1
                while (i <= season.get("episode_count")):
                    episode = tmdb.TV_Episodes(tv_id, season.get("season_number"), i)
                    episode.info()
                    print("episode number " + str(episode.episode_number) + ": " + episode.overview)
                    sql = "INSERT INTO episode (season_id,episode_num,synopsis) VALUES(%s,%s,%s)"
                    cursor.execute(sql, (episode.season_number, i, episode.overview[:255]))
                    sql ="INSERT INTO season_episodes(season_season_id,episodes_season_id) VALUES(%s,%s)"
                    cursor.execute(sql,(season.get("id"),episode.id))
                    i += 1
            db.commit()
finally:
    db.close()

