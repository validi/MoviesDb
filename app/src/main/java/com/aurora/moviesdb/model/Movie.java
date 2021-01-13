package com.aurora.moviesdb.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Parcelable , Serializable
{

    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
    @SerializedName("metadata")
    @Expose
    public Metadata metadata;
    public final static Creator<Movie> CREATOR = new Creator<Movie>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return (new Movie[size]);
        }

    };

    public Movie(Parcel in) {
        in.readList(this.data, (Datum.class.getClassLoader()));
        this.metadata = ((Metadata) in.readValue((Metadata.class.getClassLoader())));
    }

    public Movie() {
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        dest.writeValue(metadata);
    }

    public int describeContents() {
        return 0;
    }

    public static class Metadata implements Parcelable {

        @SerializedName("current_page")
        @Expose
        private String currentPage;
        @SerializedName("per_page")
        @Expose
        private Integer perPage;
        @SerializedName("page_count")
        @Expose
        private Integer pageCount;
        @SerializedName("total_count")
        @Expose
        private Integer totalCount;
        public final static Creator<Metadata> CREATOR = new Creator<Metadata>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Metadata createFromParcel(Parcel in) {
                return new Metadata(in);
            }

            public Metadata[] newArray(int size) {
                return (new Metadata[size]);
            }

        };

        protected Metadata(Parcel in) {
            this.currentPage = ((String) in.readValue((String.class.getClassLoader())));
            this.perPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.pageCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.totalCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Metadata() {
        }

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public Integer getPerPage() {
            return perPage;
        }

        public void setPerPage(Integer perPage) {
            this.perPage = perPage;
        }

        public Integer getPageCount() {
            return pageCount;
        }

        public void setPageCount(Integer pageCount) {
            this.pageCount = pageCount;
        }

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(currentPage);
            dest.writeValue(perPage);
            dest.writeValue(pageCount);
            dest.writeValue(totalCount);
        }

        public int describeContents() {
            return 0;
        }

    }

   public static class Datum implements Parcelable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("poster")
        @Expose
        private String poster;
        @SerializedName("year")
        @Expose
        private String year;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("imdb_rating")
        @Expose
        private String imdbRating;
        @SerializedName("genres")
        @Expose
        private List<String> genres = null;
        @SerializedName("images")
        @Expose
        private List<String> images = null;
        public final static Creator<Datum> CREATOR = new Creator<Datum>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Datum createFromParcel(Parcel in) {
                return new Datum(in);
            }

            public Datum[] newArray(int size) {
                return (new Datum[size]);
            }

        };

        protected Datum(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.title = ((String) in.readValue((String.class.getClassLoader())));
            this.poster = ((String) in.readValue((String.class.getClassLoader())));
            this.year = ((String) in.readValue((String.class.getClassLoader())));
            this.country = ((String) in.readValue((String.class.getClassLoader())));
            this.imdbRating = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.genres, (String.class.getClassLoader()));
            in.readList(this.images, (String.class.getClassLoader()));
        }

        public Datum() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getImdbRating() {
            return imdbRating;
        }

        public void setImdbRating(String imdbRating) {
            this.imdbRating = imdbRating;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(title);
            dest.writeValue(poster);
            dest.writeValue(year);
            dest.writeValue(country);
            dest.writeValue(imdbRating);
            dest.writeList(genres);
            dest.writeList(images);
        }

        public int describeContents() {
            return 0;
        }

    }
}
