package com.medvirumal.ecomstore.db;

import com.medvirumal.ecomstore.db.common.Converters;
import com.medvirumal.ecomstore.viewobject.AboutUs;
import com.medvirumal.ecomstore.viewobject.Basket;
import com.medvirumal.ecomstore.viewobject.Blog;
import com.medvirumal.ecomstore.viewobject.Category;
import com.medvirumal.ecomstore.viewobject.CategoryMap;
import com.medvirumal.ecomstore.viewobject.City;
import com.medvirumal.ecomstore.viewobject.Comment;
import com.medvirumal.ecomstore.viewobject.CommentDetail;
import com.medvirumal.ecomstore.viewobject.Country;
import com.medvirumal.ecomstore.viewobject.DeletedObject;
import com.medvirumal.ecomstore.viewobject.DiscountProduct;
import com.medvirumal.ecomstore.viewobject.FavouriteProduct;
import com.medvirumal.ecomstore.viewobject.FeaturedProduct;
import com.medvirumal.ecomstore.viewobject.HistoryProduct;
import com.medvirumal.ecomstore.viewobject.Image;
import com.medvirumal.ecomstore.viewobject.LatestProduct;
import com.medvirumal.ecomstore.viewobject.LikedProduct;
import com.medvirumal.ecomstore.viewobject.Noti;
import com.medvirumal.ecomstore.viewobject.PSAppInfo;
import com.medvirumal.ecomstore.viewobject.PSAppVersion;
import com.medvirumal.ecomstore.viewobject.Product;
import com.medvirumal.ecomstore.viewobject.ProductAttributeDetail;
import com.medvirumal.ecomstore.viewobject.ProductAttributeHeader;
import com.medvirumal.ecomstore.viewobject.ProductCollection;
import com.medvirumal.ecomstore.viewobject.ProductCollectionHeader;
import com.medvirumal.ecomstore.viewobject.ProductColor;
import com.medvirumal.ecomstore.viewobject.ProductListByCatId;
import com.medvirumal.ecomstore.viewobject.ProductMap;
import com.medvirumal.ecomstore.viewobject.ProductSpecs;
import com.medvirumal.ecomstore.viewobject.Rating;
import com.medvirumal.ecomstore.viewobject.RelatedProduct;
import com.medvirumal.ecomstore.viewobject.ShippingMethod;
import com.medvirumal.ecomstore.viewobject.Shop;
import com.medvirumal.ecomstore.viewobject.ShopByTagId;
import com.medvirumal.ecomstore.viewobject.ShopMap;
import com.medvirumal.ecomstore.viewobject.ShopTag;
import com.medvirumal.ecomstore.viewobject.SubCategory;
import com.medvirumal.ecomstore.viewobject.TransactionDetail;
import com.medvirumal.ecomstore.viewobject.TransactionObject;
import com.medvirumal.ecomstore.viewobject.User;
import com.medvirumal.ecomstore.viewobject.UserLogin;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


/**
 * Created by Panacea-Soft on 11/20/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Database(entities = {
        Image.class,
        Category.class,
        User.class,
        UserLogin.class,
        AboutUs.class,
        Product.class,
        LatestProduct.class,
        DiscountProduct.class,
        FeaturedProduct.class,
        SubCategory.class,
        ProductListByCatId.class,
        Comment.class,
        CommentDetail.class,
        ProductColor.class,
        ProductSpecs.class,
        RelatedProduct.class,
        FavouriteProduct.class,
        LikedProduct.class,
        ProductAttributeHeader.class,
        ProductAttributeDetail.class,
        Noti.class,
        TransactionObject.class,
        ProductCollectionHeader.class,
        ProductCollection.class,
        TransactionDetail.class,
        Basket.class,
        HistoryProduct.class,
        Shop.class,
        ShopTag.class,
        Blog.class,
        Rating.class,
        ShippingMethod.class,
        ShopByTagId.class,
        ProductMap.class,
        ShopMap.class,
        CategoryMap.class,
        PSAppInfo.class,
        PSAppVersion.class,
        DeletedObject.class,
        Country.class,
        City.class

}, version = 7, exportSchema = false)
//V2.4 = DBV 7
//V2.3 = DBV 7
//V2.2 = DBV 7
//V2.1 = DBV 7
//V2.0 = DBV 7
//V1.9 = DBV 7
//V1.8 = DBV 7
//V1.7 = DBV 6
//V1.6 = DBV 5
//V1.5 = DBV 4
//V1.4 = DBV 3
//V1.3 = DBV 2


@TypeConverters({Converters.class})

public abstract class PSCoreDb extends RoomDatabase {

    abstract public UserDao userDao();

    abstract public ProductColorDao productColorDao();

    abstract public ProductSpecsDao productSpecsDao();

    abstract public ProductAttributeHeaderDao productAttributeHeaderDao();

    abstract public ProductAttributeDetailDao productAttributeDetailDao();

    abstract public BasketDao basketDao();

    abstract public HistoryDao historyDao();

    abstract public AboutUsDao aboutUsDao();

    abstract public ImageDao imageDao();

    abstract public CountryDao countryDao();

    abstract public CityDao cityDao();

    abstract public RatingDao ratingDao();

    abstract public CommentDao commentDao();

    abstract public CommentDetailDao commentDetailDao();

    abstract public ProductDao productDao();

    abstract public CategoryDao categoryDao();

    abstract public SubCategoryDao subCategoryDao();

    abstract public NotificationDao notificationDao();

    abstract public ProductCollectionDao productCollectionDao();

    abstract public TransactionDao transactionDao();

    abstract public TransactionOrderDao transactionOrderDao();

    abstract public ShopDao shopDao();

    abstract public BlogDao blogDao();

    abstract public ShippingMethodDao shippingMethodDao();

    abstract public ProductMapDao productMapDao();

    abstract public CategoryMapDao categoryMapDao();

    abstract public PSAppInfoDao psAppInfoDao();

    abstract public PSAppVersionDao psAppVersionDao();

    abstract public DeletedObjectDao deletedObjectDao();


//    /**
//     * Migrate from:
//     * version 1 - using Room
//     * to
//     * version 2 - using Room where the {@link } has an extra field: addedDateStr
//     */
//    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE news "
//                    + " ADD COLUMN addedDateStr INTEGER NOT NULL DEFAULT 0");
//        }
//    };

    /* More migration write here */
}