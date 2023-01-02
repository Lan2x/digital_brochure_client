package com.example.digitalbrochureapp.admin;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.digitalbrochureapp.R;
import com.example.digitalbrochureapp.admin.brochure.BrochureFragment;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    public PDFView getTextView() {
        return findViewById(R.id.aaaaa);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_main);

        toolbar = findViewById(R.id.adminToolBar);
        drawerLayout = findViewById(R.id.admin_drawer_layout);
        setSupportActionBar(toolbar);
        toolbar.setTitle("ADMIN DIGITAL BROCHURE ");

        navigationView = findViewById(R.id.admin_navigation_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Navigation drawer item click listener
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        Fragment homeFragment = new HomeFragment();
                        FragmentTransaction home_ft = getSupportFragmentManager().beginTransaction();
                        home_ft.replace(R.id.admin_fragment_container, homeFragment);
                        home_ft.commit();
                        // set the title of the action bar and highlight the menu item
                        toolbar.setTitle("CS DIGITAL BROCHURE ADMIN");
                        item.setChecked(true);
                        break;
                    case R.id.brochure:
                        Fragment brochureFragment = new BrochureFragment();
                        FragmentTransaction brochure_ft = getSupportFragmentManager().beginTransaction();
                        brochure_ft.replace(R.id.admin_fragment_container, brochureFragment);
                        brochure_ft.commit();
                        // set the title of the action bar and highlight the menu item
                        toolbar.setTitle("ADMIN Brochure");
                        item.setChecked(true);
                        break;
                    case R.id.programs_offered:
                        Fragment programsOfferedFragment = new ProgramsOfferedFragment();
                        FragmentTransaction programs_ft = getSupportFragmentManager().beginTransaction();
                        programs_ft.replace(R.id.admin_fragment_container, programsOfferedFragment);
                        programs_ft.commit();
                        // set the title of the action bar and highlight the menu item
                        toolbar.setTitle("ADMIN Programs Offered");
                        item.setChecked(true);
                        break;
                    case R.id.procedure:
                        Fragment proceduresFragment = new ProceduresFragment();
                        FragmentTransaction procedures_ft = getSupportFragmentManager().beginTransaction();
                        procedures_ft.replace(R.id.admin_fragment_container, proceduresFragment);
                        procedures_ft.commit();
                        toolbar.setTitle("ADMIN Procedures");
                        item.setChecked(true);
                        break;
                    case R.id.events:
                        Fragment eventsFragments = new EventsFragment();
                        FragmentTransaction events_ft = getSupportFragmentManager().beginTransaction();
                        events_ft.replace(R.id.admin_fragment_container, eventsFragments);
                        events_ft.commit();
                        toolbar.setTitle("ADMIN Events");
                        item.setChecked(true);
                        break;
                    case R.id.projects:
                        Fragment projectsFragments = new ProjectsFragment();
                        FragmentTransaction projects_ft = getSupportFragmentManager().beginTransaction();
                        projects_ft.replace(R.id.admin_fragment_container, projectsFragments);
                        projects_ft.commit();
                        toolbar.setTitle("ADMIN Projects");
                        item.setChecked(true);
                        break;
                    case R.id.announcements:
                        Fragment announcementsFragments = new AnnouncementsFragment();
                        FragmentTransaction announcements_ft = getSupportFragmentManager().beginTransaction();
                        announcements_ft.replace(R.id.admin_fragment_container, announcementsFragments);
                        announcements_ft.commit();
                        toolbar.setTitle("ADMIN Announcements");
                        item.setChecked(true);
                        break;
                    case R.id.manuals:
                        Fragment manualFragments = new ManualsFragment();
                        FragmentTransaction manuals_ft = getSupportFragmentManager().beginTransaction();
                        manuals_ft.replace(R.id.admin_fragment_container, manualFragments);
                        manuals_ft.commit();
                        toolbar.setTitle("ADMIN Manuals");
                        item.setChecked(true);
                        break;
                    case R.id.inbox:
                        Fragment inboxFragments = new InboxFragment();
                        FragmentTransaction inbox_ft = getSupportFragmentManager().beginTransaction();
                        inbox_ft.replace(R.id.admin_fragment_container, inboxFragments);
                        inbox_ft.commit();
                        toolbar.setTitle("ADMIN Inbox");
                        item.setChecked(true);
                        break;
                    case R.id.notification:
                        Fragment notificationFragment = new NotificationsFragment();
                        FragmentTransaction notification_ft = getSupportFragmentManager().beginTransaction();
                        notification_ft.replace(R.id.admin_fragment_container, notificationFragment);
                        notification_ft.commit();
                        toolbar.setTitle("ADMIN Notifications");
                        item.setChecked(true);
                        break;
                    case R.id.favorite:
                        Fragment favoriteFragment = new MyFavoritesFragment();
                        FragmentTransaction favorite_ft = getSupportFragmentManager().beginTransaction();
                        favorite_ft.replace(R.id.admin_fragment_container, favoriteFragment);
                        favorite_ft.commit();
                        toolbar.setTitle("ADMIN Favorites");
                        item.setChecked(true);
                        break;
                    case R.id.help_support:
                        Fragment helpFragment = new HelpSupportFragment();
                        FragmentTransaction help_ft = getSupportFragmentManager().beginTransaction();
                        help_ft.replace(R.id.admin_fragment_container, helpFragment);
                        help_ft.commit();
                        toolbar.setTitle("ADMIN Help & Support");
                        item.setChecked(true);
                        break;
                    case R.id.about_us:
                        Fragment aboutFragment = new AboutUsFragment();
                        FragmentTransaction about_ft = getSupportFragmentManager().beginTransaction();
                        about_ft.replace(R.id.admin_fragment_container, aboutFragment);
                        about_ft.commit();
                        toolbar.setTitle("ADMIN About Us");
                        item.setChecked(true);
                        break;
                    case R.id.terms_conditions:
                        Fragment termsFragment = new TermsConditionsFragment();
                        FragmentTransaction terms_ft = getSupportFragmentManager().beginTransaction();
                        terms_ft.replace(R.id.admin_fragment_container, termsFragment);
                        terms_ft.commit();
                        toolbar.setTitle("ADMIN Terms and Conditions");
                        item.setChecked(true);
                        break;
                    case R.id.privacy_policy:
                        Fragment privacyFragment = new PrivacyPolicyFragment();
                        FragmentTransaction privacy_ft = getSupportFragmentManager().beginTransaction();
                        privacy_ft.replace(R.id.admin_fragment_container, privacyFragment);
                        privacy_ft.commit();
                        toolbar.setTitle("ADMIN Privacy Policy");
                        item.setChecked(true);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // If you need to set image to navigation header image or setText for header TextView  follow the code below

        View headerView = navigationView.getHeaderView(0);

        TextView textView = headerView.findViewById(R.id.header_textView);
        ImageView imageView = headerView.findViewById(R.id.header_imageView);

        // Set navigation header text
        textView.setText("username.digitalbrochure");

    }
}