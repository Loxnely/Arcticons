package com.donnnno.arcticons.applications;

import androidx.annotation.NonNull;

import com.donnnno.arcticons.R;
import candybar.lib.applications.CandyBarApplication;
import candybar.lib.items.Request;

public class CandyBar extends CandyBarApplication {

    @NonNull
    @Override
    public Class<?> getDrawableClass() {
        return R.drawable.class;
    }

    @NonNull
    @Override
    public Configuration onInit() {
        Configuration configuration = new Configuration();

        // Icons
        configuration.setAutomaticIconsCountEnabled(false);
        configuration.setCustomIconsCount(getResources().getInteger(R.integer.custom_icons_count));
        configuration.setGenerateAppFilter(true);
        configuration.setShowTabAllIcons(true);
        configuration.setCategoryForTabAllIcons(new String[]{
                "New", "Folders", "Calendar", "Google", "Microsoft", "Games",
                "System", "Emoji", "Symbols", "Numbers", "Letters", "0-9", "A-Z"
        });
        configuration.setShadowEnabled(false);

        // Home screen
        configuration.setDonationLinks(new DonationLink[]{
                new DonationLink(
                        "paypal",
                        "PayPal",
                        "Support me on Paypal",
                        "https://www.paypal.me/onnovdd"),
                new DonationLink(
                        "liberapay",
                        "Liberapay",
                        "Support me on Liberapay",
                        "https://liberapay.com/Donno/"),
                new DonationLink(
                        "kofi",
                        "Ko-Fi",
                        "Support me on Ko-Fi",
                        "https://ko-fi.com/donno_")
        });

        configuration.setOtherApps(new OtherApp[]{
                new OtherApp(
                        "arcticons",
                        "Arcticons",
                        "Arcticons, with white lines",
                        "https://play.google.com/store/apps/details?id=com.donnnno.arcticons"),
                new OtherApp(
                        "arcticons_black",
                        "Arcticons Black",
                        "Arcticons, with black lines.",
                        "https://play.google.com/store/apps/details?id=com.donnnno.arcticons.light"),
                new OtherApp(
                        "arcticons_material_you",
                        "Arcticons Material You",
                        "Arcticons, but with a material you flavor!",
                        "https://play.google.com/store/apps/details?id=com.donnnno.arcticons.you.play"),
                new OtherApp(
                        "arcticons_day_night",
                        "Arcticons Day & Night",
                        "An experimental version of Arcticons that switches between dark & light mode.",
                        "https://github.com/Donnnno/Arcticons/releases")
        });

        // Icon requests
        configuration.setEmailBodyGenerator(requests -> {
            StringBuilder emailBody = new StringBuilder();
            boolean first = true;

            for (Request request : requests) {
                if (!first) {
                    emailBody.append("\r\n\r\n");
                } else {
                    first = false;
                }

                emailBody.append(request.getName())
                        .append("\r\n")
                        .append(request.getActivity())
                        .append("\r\n");
            }

            return emailBody.toString();
        });

        configuration.setFilterRequestHandler((request) -> {
            String pkg = request.getPackageName();
            if (pkg == null) return true;
            if (pkg.startsWith("org.chromium.webapk")
                    || pkg.startsWith("com.sec.android.app.sbrowser.webapk")
                    || pkg.endsWith("com.google.android.archive.ReactivateActivity")) {
                request.setAvailableForRequest(false);
                request.setInfoText("This icon is a web shortcut and not associated with an Android app. Unfortunately it cannot be requested at this time.\n\nIn many launchers, you can long-press the app icon in the drawer and pick an existing icon from the icon pack.");
            }
            return true;
        });

        return configuration;
    }
}
