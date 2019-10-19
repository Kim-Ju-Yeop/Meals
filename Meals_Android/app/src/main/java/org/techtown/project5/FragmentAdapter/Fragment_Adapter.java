package org.techtown.project5.FragmentAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.techtown.project5.Fragment.Breakfast;
import org.techtown.project5.Fragment.Dinner;
import org.techtown.project5.Fragment.Lunch;
import org.techtown.project5.R;

public class Fragment_Adapter extends FragmentStatePagerAdapter {

    private int mPageCount;

    public Fragment_Adapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.mPageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                Breakfast breakfast = new Breakfast();
                return breakfast;

            case 1:
                Lunch lunch = new Lunch();
                return lunch;

            case 2:
                Dinner dinner = new Dinner();
                return dinner;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}
