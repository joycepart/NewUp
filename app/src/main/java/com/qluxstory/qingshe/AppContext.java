package com.qluxstory.qingshe;

import android.content.Context;
import android.content.SharedPreferences;

import com.qluxstory.qingshe.common.base.BaseApplication;
import com.qluxstory.qingshe.common.utils.SerializableUtils;
import com.qluxstory.qingshe.home.entity.Address;
import com.qluxstory.qingshe.home.entity.Consignee;
import com.qluxstory.qingshe.home.entity.ProductDetails;
import com.qluxstory.qingshe.issue.entity.IssueProduct;
import com.qluxstory.qingshe.me.entity.User;

import java.io.IOException;
import java.io.StreamCorruptedException;


/**
 */
public class AppContext  extends BaseApplication {
    private static AppContext instance;
    public static final String USER = "user";
    private static User mUser = null;
    public static final String CONSIGNEE = "consignee";
    private static Consignee mConsignee = null;
    public static final String ADDRESS = "address";
    private static Address mAddress = null;
    public static final String PROUDCTDETAILS = "productDetails";
    private static ProductDetails mProductDetails = null;
    public static final String ISSUEPROUDCTD = "issueProduct";
    private static IssueProduct mIssueProduct = null;

    /**
     * 夺宝之商品信息
     * @param issueProduct
     */
    public void setIssueProduct(IssueProduct issueProduct) {
        String str = "";
        try {
            str = SerializableUtils.obj2Str(issueProduct);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        this.editor.putString(ISSUEPROUDCTD, str);
        this.editor.commit();
        mIssueProduct = issueProduct;
    }

    public IssueProduct getIssueProduct() {
        if (mIssueProduct == null) {
            mIssueProduct = new IssueProduct();
            // 获取序列化的数据
            String str = this.sp.getString(ISSUEPROUDCTD, "");

            try {
                Object obj = SerializableUtils.str2Obj(str);
                if (obj != null) {
                    mIssueProduct = (IssueProduct) obj;
                }

            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mIssueProduct;
    }
    /**
     * 专业养护之商品信息
     * @param productDetails
     */
    public void setProductDetails(ProductDetails productDetails) {
        String str = "";
        try {
            str = SerializableUtils.obj2Str(productDetails);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        this.editor.putString(PROUDCTDETAILS, str);
        this.editor.commit();
        mProductDetails = productDetails;
    }

    public ProductDetails getProductDetails() {
        if (mProductDetails == null) {
            mProductDetails = new ProductDetails();
            // 获取序列化的数据
            String str = this.sp.getString(PROUDCTDETAILS, "");

            try {
                Object obj = SerializableUtils.str2Obj(str);
                if (obj != null) {
                    mProductDetails = (ProductDetails) obj;
                }

            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mProductDetails;
    }
    /**
     * 保存收货人信息
     * @param consignee
     */
    public void setConsignee(Consignee consignee) {
        String str = "";
        try {
            str = SerializableUtils.obj2Str(consignee);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        this.editor.putString(CONSIGNEE, str);
        this.editor.commit();
        mConsignee = consignee;
    }

    public Consignee getConsignee() {
        if (mConsignee == null) {
            mConsignee = new Consignee();
            // 获取序列化的数据
            String str = this.sp.getString(CONSIGNEE, "");

            try {
                Object obj = SerializableUtils.str2Obj(str);
                if (obj != null) {
                    mConsignee = (Consignee) obj;
                }

            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mConsignee;
    }


    /**
     * 保存寄送地址信息
     * @param address
     */
    public void setAddress(Address address) {
        String str = "";
        try {
            str = SerializableUtils.obj2Str(address);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        this.editor.putString(ADDRESS, str);
        this.editor.commit();
        mAddress = address;
    }

    public Address getAddress() {
        if (mAddress == null) {
            mAddress = new Address();
            // 获取序列化的数据
            String str = this.sp.getString(ADDRESS, "");

            try {
                Object obj = SerializableUtils.str2Obj(str);
                if (obj != null) {
                    mAddress = (Address) obj;
                }

            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mAddress;
    }




    private SharedPreferences.Editor editor;
    private SharedPreferences sp;
    /**
     * 清单文件名称
     */
    public static final String SP_NAME = "qingshe_app";

//    public AppContext(Context mContext) {
//        this.sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
//        this.editor = this.sp.edit();
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.sp = this.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        this.editor = this.sp.edit();
    }

    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static AppContext getInstance() {
        return instance;
    }
    /**
     * 保存用户信息
     * @param user
     */
    public void setUser(User user) {
        String str = "";
        try {
            str = SerializableUtils.obj2Str(user);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        this.editor.putString(USER, str);
        this.editor.commit();
        mUser = user;
    }
    /**
     * 获取用户信息
     * @return
     */
    public User getUser() {
        if (mUser == null) {
            mUser = new User();
            // 获取序列化的数据
            String str = this.sp.getString(USER, "");

            try {
                Object obj = SerializableUtils.str2Obj(str);
                if (obj != null) {
                    mUser = (User) obj;
                }

            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mUser;
    }
}
