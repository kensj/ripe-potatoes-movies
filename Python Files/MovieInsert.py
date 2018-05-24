import tmdbsimple as tmdb
import time
import json
import pymysql
tmdb.API_KEY = 'API-KEY'

with open("MovieList.txt", 'r') as f:
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
            movie_title = name
            search.movie(query=movie_title)
            movie_id = search.results[0]['id']
            movie = tmdb.Movies(movie_id)
            movie.info()
            sql="INSERT INTO content(contentid,is_featured,name,num_rating,sum_rating) VALUES(%s,%s,%s,%s,%s)"
            sum_rating=int(movie.vote_average*movie.vote_count)
            rating=int(movie.vote_average)
            cursor.execute(sql,(movie.id,0,movie.title,rating,sum_rating))
            year=movie.release_date[:4]
            sql = "INSERT INTO film (box_office_revenue,budget,contentid) VALUES(%s,%s,%s)"
            cursor.execute(sql,(movie.revenue,movie.budget,movie.id))
            sql = "INSERT INTO media(critic_rating,release_date,synopsis,year,contentid) VALUES (%s, %s, %s, %s,%s)"
            cursor.execute(sql, (0, movie.release_date, movie.overview[:255], year, movie.id))
            #for genre in movie.genres:
            #    sql="INSERT INTO content_genres (media_contentid,genres_text) VALUES (%s,%s)"
            #    cursor.execute(sql,(movie.id,genre.get("name")))
            print("Title: " + movie.title)
            print("Release date: " + movie.release_date)
            print("Budget: " + (str)(movie.budget))
            movie.credits()
            cast=movie.cast
            for actor in cast:
                sql = "INSERT INTO media_cast (media_contentid,cast) VALUES(%s,%s)"
                cursor.execute(sql, (movie.id, actor.get('name')))
                print("actor name: " +actor.get("name"))
            #movie.credits()
            #cast = movie.cast
            db.commit()
finally:
    db.close()

