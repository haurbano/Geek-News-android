package movil.salt.weeknews.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by pc on 09/06/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    List<TitleFragment> data;

    public PagerAdapter(FragmentManager fm, List<TitleFragment> data) {
        super(fm);
        this.data = data;

    }

    @Override
    public Fragment getItem(int i) {
        return data.get(i);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getTtitle();
    }
}
