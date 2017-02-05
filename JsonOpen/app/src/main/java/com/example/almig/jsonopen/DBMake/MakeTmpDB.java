package com.example.almig.jsonopen.DBMake;

import android.content.Context;

import com.example.almig.jsonopen.Object.Gym;
import com.example.almig.jsonopen.Object.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by almig on 2017-02-04.
 */

public class MakeTmpDB {
    private Context context;

    public MakeTmpDB(Context _context) {
        context = _context;
    }

    public ArrayList<Gym> makeGymDB() {
        ArrayList<Gym> arrGym = new ArrayList<>();

        try {
            String json = null;
            InputStream is = context.getAssets().open("gymlist.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray memberArray = (JSONArray) jsonObject.get("DATA");

            for (int i = 0; i < 400; i++) {
                JSONObject tempObj = (JSONObject) memberArray.get(i);
                Gym gym = new Gym();
                gym.setManageOrgan((String) tempObj.get("MANAGE_ORGAN"));
                gym.setAddr((String) tempObj.get("ADDRESS"));
                gym.setCenterName((String) tempObj.get("CENTER_NAME"));
                gym.setTel((String) tempObj.get("TEL"));
                gym.setUrl((String) tempObj.get("HOMEPAGE"));

                arrGym.add(gym);
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            return arrGym;
        }
    }

    public ArrayList<User> makeUserDB() {
        ArrayList<User> arrUser = new ArrayList<>();

        try {
            String json = null;
            InputStream is = context.getAssets().open("userlist.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray memberArray = (JSONArray) jsonObject.get("DATA");

            for (int i = 0; i < 40; i++) {
                JSONObject tempObj = (JSONObject) memberArray.get(i);
                User user = new User();
                user.setName((String) tempObj.get("NAME"));
                user.setAddr((String) tempObj.get("ADDRESS"));
                user.setGender((String) tempObj.get("SEX"));
                user.setAge(Integer.parseInt((String)tempObj.get("AGE")));
                user.setId((String) tempObj.get("ID"));
                user.setFavorspor1((String) tempObj.get("FAVORSPOR1"));
                user.setFavorspor2((String) tempObj.get("FAVORSPOR2"));
                user.setFavorspor3((String) tempObj.get("FAVORSPOR3"));

                arrUser.add(user);
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            return arrUser;
        }
    }
}
