import tmdbsimple as tmdb
import urllib.request
tmdb.API_KEY = 'API-KEY'
search = tmdb.Search()
movie_title="The Shape Of Water"
search.movie(query=movie_title)
movie_id  = search.results[0]['id']
movie = tmdb.Movies(movie_id)
movie.info()
config=tmdb.Configuration();
print(movie.poster_path)
config.info()
base="http://image.tmdb.org/t/p/"
size="original/"
path=movie.poster_path
url=(base+size+path)
print(url)
urllib.request.urlretrieve(url,"abc.jpg")
#print(movie.images())