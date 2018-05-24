import tmdbsimple as tmdb
import time
import json
import pymysql
tmdb.API_KEY = 'API-KEY'


db= pymysql.connect("localhost","ripepotatoes","changeit","ripepotatoes")

try:
    with db.cursor() as cursor:
        db.set_charset('utf8')
        cursor.execute('SET NAMES utf8;')
        cursor.execute('SET CHARACTER SET utf8;')
        cursor.execute('SET character_set_connection=utf8;')

        # insert a single movie
        search = tmdb.Search()
        movie_title = "Moana"
        search.movie(query=movie_title)
        movie_id = search.results[0]['id']
        movie = tmdb.Movies(movie_id)
        movie.info()
        #for genre in movie.genres:
        #    sql="INSERT INTO content_genres (media_contentid,genres_text) VALUES (%s,%s)"
        #    cursor.execute(sql,(movie.id,genre.get("name")))
        print("Title: " + movie.title)
        print("Release date: " + movie.release_date)
        print("Budget: " + (str)(movie.budget))
        movie.credits()
        cast=movie.cast
        for actor in cast:
            print("Actor Name: " + actor.get('name'))
            person = tmdb.People(actor.get('id'))
            person.info()
            print(person.name)
            sql = "INSERT INTO content (contentid,is_featured,name,num_rating,sum_ratings) VALUES(%s,%s,%s,%s,%s )"
            cursor.execute(sql, (person.id,0, person.name,0,0))
            print("actor name: " +actor.get("name"))
            sql = "INSERT INTO celebrity (bio,birthday,is_actor,is_director,is_misc,is_writer,picture,contentid) VALUES(%s,%s,%s,%s,%s,%s,%s,%s )"
            cursor.execute(sql, (person.biography[:255],person.birthday,1,0,0,0,"DwayneJohnson.jpg",person.id))
            sql = "INSERT INTO celebrity_filmography(celebrity_contentid,filmography_contentid)"
        #movie.credits()
        #cast = movie.cast
        db.commit()
finally:
    db.close()

