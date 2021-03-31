package com.example.museums.view.activities.tabs;
//
//import android.os.Bundle;
//
//import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.example.museums.R;
//import com.example.museums.view.fragments.user.exhibitions.Exhibitions;
//import com.example.museums.view.fragments.user.exhibits.Exhibits;
//import com.example.museums.view.fragments.user.LikedExhbViewPager;
//import com.example.museums.view.services.Listeners.KeyboardListenerHideOptionalBlock;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
//
public class UserTab extends AppCompatActivity {
//
//    public static final String LOGIN_USER_KEY = "login_key";
//    private BottomNavigationView menuTab;
//    public static final String ID_USER_KEY = "user_key";
//    private Integer idUser;
//    private String login;
//
//    private boolean currState = false;
//    private Exhibits exhibits;
//    private Exhibitions exhibitions;
//    private LikedExhbViewPager likedExhbViewPager;
//
//    private void initViews() {
//        menuTab = (BottomNavigationView) findViewById(R.id.user_btn_nav_menu);
//        KeyboardVisibilityEvent.setEventListener(this, new KeyboardListenerHideOptionalBlock(menuTab));
//    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tab_user);
//        initViews();
//        Bundle b = getIntent().getExtras();
//        if (b != null) {
//            idUser = b.getInt(ID_USER_KEY);
//            login = b.getString(LOGIN_USER_KEY);
//            likedExhbViewPager = LikedExhbViewPager.newInstance(login, idUser);
//            exhibits = Exhibits.newInstance(idUser);
//            exhibitions = Exhibitions.newInstance(idUser);
//        }
//
//        setInitialPage();
//
//        menuTab.setOnNavigationItemSelectedListener(menuItem -> {
//            switch (menuItem.getItemId()) {
//                case R.id.menu_user_exhibits:
//                    final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                    ft.replace(R.id.container_tab_user, exhibits)
//                            .addToBackStack(Exhibits.class.toString())
//                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
//                    menuTab.getMenu().findItem(R.id.menu_user_exhibits).setChecked(true);
//                    break;
//                case R.id.menu_user_exhibitions:
//                    final FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
//                    ft1.replace(R.id.container_tab_user, exhibitions)
//                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                            .addToBackStack(Exhibitions.class.toString()).commit();
//                    menuTab.getMenu().findItem(R.id.menu_user_exhibitions).setChecked(true);
//                    break;
//                case R.id.menu_user_home:
//                    final FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
//                    ft2.replace(R.id.container_tab_user, likedExhbViewPager)
//                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                            .addToBackStack(LikedExhbViewPager.class.toString()).commit();
//                    menuTab.getMenu().findItem(R.id.menu_user_home).setChecked(true);
//                    break;
//            }
//            return false;
//        });
//    }
//
//    private void setInitialPage() {
//        if (!currState) {
//            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            currState = true;
//            ft.replace(R.id.container_tab_user, exhibits).addToBackStack(Exhibits.class.toString())
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
//            menuTab.getMenu().findItem(R.id.menu_user_exhibits).setChecked(true);
//        }
//    }
}
//
