package com.astrolightz.pocketbox;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textview.MaterialTextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * A collection of utility methods
 */
public class Utilities
{
    /**
     * Rounds a number to a specified number of decimal places
     * @param number         The number to round
     * @param place  The number of decimal places to round to
     * @return               The rounded number
     */
    public static double roundTo(Number number, int place)
    {
        double factor = Math.pow(10, place);
        return Math.round(number.doubleValue() * factor) / factor;
    }

    /**
     * Calculate the number of days between two dates
     * @param startDate The start date
     * @param endDate  The end date
     * @param dateFormat The format of the dates. Leave blank for default: "MMMM dd, yyyy"
     * @return The number of days between the two dates
     */
    public static long calcualteDaysApart(String startDate, String endDate, String dateFormat)
    {
        if (dateFormat.isEmpty())
        {
            dateFormat = "MMMM dd, yyyy";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        Log.d("DEBUG", "Start: " + start);
        Log.d("DEBUG", "End: " + end);

        // Calculate days apart
        return ChronoUnit.DAYS.between(start, end);
    }


    // =============================================================================================
    //                                      NUMBER NAME
    // =============================================================================================

    // Woah
    private static final NavigableMap<BigDecimal, String> numberNames = new TreeMap<>(Collections.reverseOrder());

    public static void adjustFontSize(String text, MaterialTextView textView)
    {
        // Size constraints
        final float MAX_SIZE = 40f;
        final float MIN_SIZE = 14f;

        // Scaling factor
        final float SCALE_FACTOR = 0.5f;

        // Adjust based on length
        float fontSize = MAX_SIZE - (text.length() * SCALE_FACTOR);
        if (fontSize < MIN_SIZE)
        {
            // Prevent too small
            fontSize = MIN_SIZE;
        }

        // Set size
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
    }

    static
    {
        numberNames.put(new BigDecimal("1000"), "Thousand");
        numberNames.put(new BigDecimal("1000000"), "Million");
        numberNames.put(new BigDecimal("1000000000"), "Billion");
        numberNames.put(new BigDecimal("1000000000000"), "Trillion");
        numberNames.put(new BigDecimal("1000000000000000"), "Quadrillion");
        numberNames.put(new BigDecimal("1000000000000000000"), "Quintillion");
        numberNames.put(new BigDecimal("1000000000000000000000"), "Sextillion");
        numberNames.put(new BigDecimal("1000000000000000000000000"), "Septillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000"), "Octillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000"), "Nonillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000"), "Decillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000"), "Undecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000"), "Duodecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000"), "Tredecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000"), "Quattuordecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000"), "Quindecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000"), "Sexdecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000"), "Septendecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000"), "Octodecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000"), "Novemdecillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000"), "Vigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000"), "Unvigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000"), "Duovigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000"), "Trevigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000"), "Quattuorvigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000"), "Quinvigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000"), "Sexvigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), "Septenvigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), "Octovigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), "Novemvigintillion");
        numberNames.put(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), "Trigintillion");
    }

    /**
     * Converts a number to a name
     * @param number The number to convert
     * @return       The name of the number
     */
    public static String getNumberName(BigDecimal number)
    {
        // Return zero if number is zero
        if (number.compareTo(BigDecimal.ZERO) == 0)
        {
            return "Zero";
        }

        for (Map.Entry<BigDecimal, String> entry : numberNames.entrySet())
        {
            BigDecimal divisor = entry.getKey();
            String name = entry.getValue();

            // Handle too big number
            if (number.compareTo(numberNames.firstKey()) >= 0)
            {
                return "nan";
            }

            // Get name
            if (number.compareTo(divisor) >= 0)
            {
                // Greater than divisor
                BigDecimal value = number.divide(divisor, 2, RoundingMode.HALF_UP);
                return value.stripTrailingZeros().toPlainString() + " " + name;
            }

        }

        // No matches, return raw number
        return number.toPlainString();
    }



    // =============================================================================================
    //                                      LICENSES
    // =============================================================================================

    public static final String appLicense = "GNU GENERAL PUBLIC LICENSE\n" +
            "Version 3, 29 June 2007\n" +
            "\n" +
            " Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>\n" +
            " Everyone is permitted to copy and distribute verbatim copies\n" +
            " of this license document, but changing it is not allowed.\n" +
            "\n" +
            "Preamble\n" +
            "\n" +
            "  The GNU General Public License is a free, copyleft license for\n" +
            "software and other kinds of works.\n" +
            "\n" +
            "  The licenses for most software and other practical works are designed\n" +
            "to take away your freedom to share and change the works.  By contrast,\n" +
            "the GNU General Public License is intended to guarantee your freedom to\n" +
            "share and change all versions of a program--to make sure it remains free\n" +
            "software for all its users.  We, the Free Software Foundation, use the\n" +
            "GNU General Public License for most of our software; it applies also to\n" +
            "any other work released this way by its authors.  You can apply it to\n" +
            "your programs, too.\n" +
            "\n" +
            "  When we speak of free software, we are referring to freedom, not\n" +
            "price.  Our General Public Licenses are designed to make sure that you\n" +
            "have the freedom to distribute copies of free software (and charge for\n" +
            "them if you wish), that you receive source code or can get it if you\n" +
            "want it, that you can change the software or use pieces of it in new\n" +
            "free programs, and that you know you can do these things.\n" +
            "\n" +
            "  To protect your rights, we need to prevent others from denying you\n" +
            "these rights or asking you to surrender the rights.  Therefore, you have\n" +
            "certain responsibilities if you distribute copies of the software, or if\n" +
            "you modify it: responsibilities to respect the freedom of others.\n" +
            "\n" +
            "  For example, if you distribute copies of such a program, whether\n" +
            "gratis or for a fee, you must pass on to the recipients the same\n" +
            "freedoms that you received.  You must make sure that they, too, receive\n" +
            "or can get the source code.  And you must show them these terms so they\n" +
            "know their rights.\n" +
            "\n" +
            "  Developers that use the GNU GPL protect your rights with two steps:\n" +
            "(1) assert copyright on the software, and (2) offer you this License\n" +
            "giving you legal permission to copy, distribute and/or modify it.\n" +
            "\n" +
            "  For the developers' and authors' protection, the GPL clearly explains\n" +
            "that there is no warranty for this free software.  For both users' and\n" +
            "authors' sake, the GPL requires that modified versions be marked as\n" +
            "changed, so that their problems will not be attributed erroneously to\n" +
            "authors of previous versions.\n" +
            "\n" +
            "  Some devices are designed to deny users access to install or run\n" +
            "modified versions of the software inside them, although the manufacturer\n" +
            "can do so.  This is fundamentally incompatible with the aim of\n" +
            "protecting users' freedom to change the software.  The systematic\n" +
            "pattern of such abuse occurs in the area of products for individuals to\n" +
            "use, which is precisely where it is most unacceptable.  Therefore, we\n" +
            "have designed this version of the GPL to prohibit the practice for those\n" +
            "products.  If such problems arise substantially in other domains, we\n" +
            "stand ready to extend this provision to those domains in future versions\n" +
            "of the GPL, as needed to protect the freedom of users.\n" +
            "\n" +
            "  Finally, every program is threatened constantly by software patents.\n" +
            "States should not allow patents to restrict development and use of\n" +
            "software on general-purpose computers, but in those that do, we wish to\n" +
            "avoid the special danger that patents applied to a free program could\n" +
            "make it effectively proprietary.  To prevent this, the GPL assures that\n" +
            "patents cannot be used to render the program non-free.\n" +
            "\n" +
            "  The precise terms and conditions for copying, distribution and\n" +
            "modification follow.\n" +
            "\n" +
            "TERMS AND CONDITIONS\n" +
            "\n" +
            "  0. Definitions.\n" +
            "\n" +
            "  \"This License\" refers to version 3 of the GNU General Public License.\n" +
            "\n" +
            "  \"Copyright\" also means copyright-like laws that apply to other kinds of\n" +
            "works, such as semiconductor masks.\n" +
            "\n" +
            "  \"The Program\" refers to any copyrightable work licensed under this\n" +
            "License.  Each licensee is addressed as \"you\".  \"Licensees\" and\n" +
            "\"recipients\" may be individuals or organizations.\n" +
            "\n" +
            "  To \"modify\" a work means to copy from or adapt all or part of the work\n" +
            "in a fashion requiring copyright permission, other than the making of an\n" +
            "exact copy.  The resulting work is called a \"modified version\" of the\n" +
            "earlier work or a work \"based on\" the earlier work.\n" +
            "\n" +
            "  A \"covered work\" means either the unmodified Program or a work based\n" +
            "on the Program.\n" +
            "\n" +
            "  To \"propagate\" a work means to do anything with it that, without\n" +
            "permission, would make you directly or secondarily liable for\n" +
            "infringement under applicable copyright law, except executing it on a\n" +
            "computer or modifying a private copy.  Propagation includes copying,\n" +
            "distribution (with or without modification), making available to the\n" +
            "public, and in some countries other activities as well.\n" +
            "\n" +
            "  To \"convey\" a work means any kind of propagation that enables other\n" +
            "parties to make or receive copies.  Mere interaction with a user through\n" +
            "a computer network, with no transfer of a copy, is not conveying.\n" +
            "\n" +
            "  An interactive user interface displays \"Appropriate Legal Notices\"\n" +
            "to the extent that it includes a convenient and prominently visible\n" +
            "feature that (1) displays an appropriate copyright notice, and (2)\n" +
            "tells the user that there is no warranty for the work (except to the\n" +
            "extent that warranties are provided), that licensees may convey the\n" +
            "work under this License, and how to view a copy of this License.  If\n" +
            "the interface presents a list of user commands or options, such as a\n" +
            "menu, a prominent item in the list meets this criterion.\n" +
            "\n" +
            "  1. Source Code.\n" +
            "\n" +
            "  The \"source code\" for a work means the preferred form of the work\n" +
            "for making modifications to it.  \"Object code\" means any non-source\n" +
            "form of a work.\n" +
            "\n" +
            "  A \"Standard Interface\" means an interface that either is an official\n" +
            "standard defined by a recognized standards body, or, in the case of\n" +
            "interfaces specified for a particular programming language, one that\n" +
            "is widely used among developers working in that language.\n" +
            "\n" +
            "  The \"System Libraries\" of an executable work include anything, other\n" +
            "than the work as a whole, that (a) is included in the normal form of\n" +
            "packaging a Major Component, but which is not part of that Major\n" +
            "Component, and (b) serves only to enable use of the work with that\n" +
            "Major Component, or to implement a Standard Interface for which an\n" +
            "implementation is available to the public in source code form.  A\n" +
            "\"Major Component\", in this context, means a major essential component\n" +
            "(kernel, window system, and so on) of the specific operating system\n" +
            "(if any) on which the executable work runs, or a compiler used to\n" +
            "produce the work, or an object code interpreter used to run it.\n" +
            "\n" +
            "  The \"Corresponding Source\" for a work in object code form means all\n" +
            "the source code needed to generate, install, and (for an executable\n" +
            "work) run the object code and to modify the work, including scripts to\n" +
            "control those activities.  However, it does not include the work's\n" +
            "System Libraries, or general-purpose tools or generally available free\n" +
            "programs which are used unmodified in performing those activities but\n" +
            "which are not part of the work.  For example, Corresponding Source\n" +
            "includes interface definition files associated with source files for\n" +
            "the work, and the source code for shared libraries and dynamically\n" +
            "linked subprograms that the work is specifically designed to require,\n" +
            "such as by intimate data communication or control flow between those\n" +
            "subprograms and other parts of the work.\n" +
            "\n" +
            "  The Corresponding Source need not include anything that users\n" +
            "can regenerate automatically from other parts of the Corresponding\n" +
            "Source.\n" +
            "\n" +
            "  The Corresponding Source for a work in source code form is that\n" +
            "same work.\n" +
            "\n" +
            "  2. Basic Permissions.\n" +
            "\n" +
            "  All rights granted under this License are granted for the term of\n" +
            "copyright on the Program, and are irrevocable provided the stated\n" +
            "conditions are met.  This License explicitly affirms your unlimited\n" +
            "permission to run the unmodified Program.  The output from running a\n" +
            "covered work is covered by this License only if the output, given its\n" +
            "content, constitutes a covered work.  This License acknowledges your\n" +
            "rights of fair use or other equivalent, as provided by copyright law.\n" +
            "\n" +
            "  You may make, run and propagate covered works that you do not\n" +
            "convey, without conditions so long as your license otherwise remains\n" +
            "in force.  You may convey covered works to others for the sole purpose\n" +
            "of having them make modifications exclusively for you, or provide you\n" +
            "with facilities for running those works, provided that you comply with\n" +
            "the terms of this License in conveying all material for which you do\n" +
            "not control copyright.  Those thus making or running the covered works\n" +
            "for you must do so exclusively on your behalf, under your direction\n" +
            "and control, on terms that prohibit them from making any copies of\n" +
            "your copyrighted material outside their relationship with you.\n" +
            "\n" +
            "  Conveying under any other circumstances is permitted solely under\n" +
            "the conditions stated below.  Sublicensing is not allowed; section 10\n" +
            "makes it unnecessary.\n" +
            "\n" +
            "  3. Protecting Users' Legal Rights From Anti-Circumvention Law.\n" +
            "\n" +
            "  No covered work shall be deemed part of an effective technological\n" +
            "measure under any applicable law fulfilling obligations under article\n" +
            "11 of the WIPO copyright treaty adopted on 20 December 1996, or\n" +
            "similar laws prohibiting or restricting circumvention of such\n" +
            "measures.\n" +
            "\n" +
            "  When you convey a covered work, you waive any legal power to forbid\n" +
            "circumvention of technological measures to the extent such circumvention\n" +
            "is effected by exercising rights under this License with respect to\n" +
            "the covered work, and you disclaim any intention to limit operation or\n" +
            "modification of the work as a means of enforcing, against the work's\n" +
            "users, your or third parties' legal rights to forbid circumvention of\n" +
            "technological measures.\n" +
            "\n" +
            "  4. Conveying Verbatim Copies.\n" +
            "\n" +
            "  You may convey verbatim copies of the Program's source code as you\n" +
            "receive it, in any medium, provided that you conspicuously and\n" +
            "appropriately publish on each copy an appropriate copyright notice;\n" +
            "keep intact all notices stating that this License and any\n" +
            "non-permissive terms added in accord with section 7 apply to the code;\n" +
            "keep intact all notices of the absence of any warranty; and give all\n" +
            "recipients a copy of this License along with the Program.\n" +
            "\n" +
            "  You may charge any price or no price for each copy that you convey,\n" +
            "and you may offer support or warranty protection for a fee.\n" +
            "\n" +
            "  5. Conveying Modified Source Versions.\n" +
            "\n" +
            "  You may convey a work based on the Program, or the modifications to\n" +
            "produce it from the Program, in the form of source code under the\n" +
            "terms of section 4, provided that you also meet all of these conditions:\n" +
            "\n" +
            "    a) The work must carry prominent notices stating that you modified\n" +
            "    it, and giving a relevant date.\n" +
            "\n" +
            "    b) The work must carry prominent notices stating that it is\n" +
            "    released under this License and any conditions added under section\n" +
            "    7.  This requirement modifies the requirement in section 4 to\n" +
            "    \"keep intact all notices\".\n" +
            "\n" +
            "    c) You must license the entire work, as a whole, under this\n" +
            "    License to anyone who comes into possession of a copy.  This\n" +
            "    License will therefore apply, along with any applicable section 7\n" +
            "    additional terms, to the whole of the work, and all its parts,\n" +
            "    regardless of how they are packaged.  This License gives no\n" +
            "    permission to license the work in any other way, but it does not\n" +
            "    invalidate such permission if you have separately received it.\n" +
            "\n" +
            "    d) If the work has interactive user interfaces, each must display\n" +
            "    Appropriate Legal Notices; however, if the Program has interactive\n" +
            "    interfaces that do not display Appropriate Legal Notices, your\n" +
            "    work need not make them do so.\n" +
            "\n" +
            "  A compilation of a covered work with other separate and independent\n" +
            "works, which are not by their nature extensions of the covered work,\n" +
            "and which are not combined with it such as to form a larger program,\n" +
            "in or on a volume of a storage or distribution medium, is called an\n" +
            "\"aggregate\" if the compilation and its resulting copyright are not\n" +
            "used to limit the access or legal rights of the compilation's users\n" +
            "beyond what the individual works permit.  Inclusion of a covered work\n" +
            "in an aggregate does not cause this License to apply to the other\n" +
            "parts of the aggregate.\n" +
            "\n" +
            "  6. Conveying Non-Source Forms.\n" +
            "\n" +
            "  You may convey a covered work in object code form under the terms\n" +
            "of sections 4 and 5, provided that you also convey the\n" +
            "machine-readable Corresponding Source under the terms of this License,\n" +
            "in one of these ways:\n" +
            "\n" +
            "    a) Convey the object code in, or embodied in, a physical product\n" +
            "    (including a physical distribution medium), accompanied by the\n" +
            "    Corresponding Source fixed on a durable physical medium\n" +
            "    customarily used for software interchange.\n" +
            "\n" +
            "    b) Convey the object code in, or embodied in, a physical product\n" +
            "    (including a physical distribution medium), accompanied by a\n" +
            "    written offer, valid for at least three years and valid for as\n" +
            "    long as you offer spare parts or customer support for that product\n" +
            "    model, to give anyone who possesses the object code either (1) a\n" +
            "    copy of the Corresponding Source for all the software in the\n" +
            "    product that is covered by this License, on a durable physical\n" +
            "    medium customarily used for software interchange, for a price no\n" +
            "    more than your reasonable cost of physically performing this\n" +
            "    conveying of source, or (2) access to copy the\n" +
            "    Corresponding Source from a network server at no charge.\n" +
            "\n" +
            "    c) Convey individual copies of the object code with a copy of the\n" +
            "    written offer to provide the Corresponding Source.  This\n" +
            "    alternative is allowed only occasionally and noncommercially, and\n" +
            "    only if you received the object code with such an offer, in accord\n" +
            "    with subsection 6b.\n" +
            "\n" +
            "    d) Convey the object code by offering access from a designated\n" +
            "    place (gratis or for a charge), and offer equivalent access to the\n" +
            "    Corresponding Source in the same way through the same place at no\n" +
            "    further charge.  You need not require recipients to copy the\n" +
            "    Corresponding Source along with the object code.  If the place to\n" +
            "    copy the object code is a network server, the Corresponding Source\n" +
            "    may be on a different server (operated by you or a third party)\n" +
            "    that supports equivalent copying facilities, provided you maintain\n" +
            "    clear directions next to the object code saying where to find the\n" +
            "    Corresponding Source.  Regardless of what server hosts the\n" +
            "    Corresponding Source, you remain obligated to ensure that it is\n" +
            "    available for as long as needed to satisfy these requirements.\n" +
            "\n" +
            "    e) Convey the object code using peer-to-peer transmission, provided\n" +
            "    you inform other peers where the object code and Corresponding\n" +
            "    Source of the work are being offered to the general public at no\n" +
            "    charge under subsection 6d.\n" +
            "\n" +
            "  A separable portion of the object code, whose source code is excluded\n" +
            "from the Corresponding Source as a System Library, need not be\n" +
            "included in conveying the object code work.\n" +
            "\n" +
            "  A \"User Product\" is either (1) a \"consumer product\", which means any\n" +
            "tangible personal property which is normally used for personal, family,\n" +
            "or household purposes, or (2) anything designed or sold for incorporation\n" +
            "into a dwelling.  In determining whether a product is a consumer product,\n" +
            "doubtful cases shall be resolved in favor of coverage.  For a particular\n" +
            "product received by a particular user, \"normally used\" refers to a\n" +
            "typical or common use of that class of product, regardless of the status\n" +
            "of the particular user or of the way in which the particular user\n" +
            "actually uses, or expects or is expected to use, the product.  A product\n" +
            "is a consumer product regardless of whether the product has substantial\n" +
            "commercial, industrial or non-consumer uses, unless such uses represent\n" +
            "the only significant mode of use of the product.\n" +
            "\n" +
            "  \"Installation Information\" for a User Product means any methods,\n" +
            "procedures, authorization keys, or other information required to install\n" +
            "and execute modified versions of a covered work in that User Product from\n" +
            "a modified version of its Corresponding Source.  The information must\n" +
            "suffice to ensure that the continued functioning of the modified object\n" +
            "code is in no case prevented or interfered with solely because\n" +
            "modification has been made.\n" +
            "\n" +
            "  If you convey an object code work under this section in, or with, or\n" +
            "specifically for use in, a User Product, and the conveying occurs as\n" +
            "part of a transaction in which the right of possession and use of the\n" +
            "User Product is transferred to the recipient in perpetuity or for a\n" +
            "fixed term (regardless of how the transaction is characterized), the\n" +
            "Corresponding Source conveyed under this section must be accompanied\n" +
            "by the Installation Information.  But this requirement does not apply\n" +
            "if neither you nor any third party retains the ability to install\n" +
            "modified object code on the User Product (for example, the work has\n" +
            "been installed in ROM).\n" +
            "\n" +
            "  The requirement to provide Installation Information does not include a\n" +
            "requirement to continue to provide support service, warranty, or updates\n" +
            "for a work that has been modified or installed by the recipient, or for\n" +
            "the User Product in which it has been modified or installed.  Access to a\n" +
            "network may be denied when the modification itself materially and\n" +
            "adversely affects the operation of the network or violates the rules and\n" +
            "protocols for communication across the network.\n" +
            "\n" +
            "  Corresponding Source conveyed, and Installation Information provided,\n" +
            "in accord with this section must be in a format that is publicly\n" +
            "documented (and with an implementation available to the public in\n" +
            "source code form), and must require no special password or key for\n" +
            "unpacking, reading or copying.\n" +
            "\n" +
            "  7. Additional Terms.\n" +
            "\n" +
            "  \"Additional permissions\" are terms that supplement the terms of this\n" +
            "License by making exceptions from one or more of its conditions.\n" +
            "Additional permissions that are applicable to the entire Program shall\n" +
            "be treated as though they were included in this License, to the extent\n" +
            "that they are valid under applicable law.  If additional permissions\n" +
            "apply only to part of the Program, that part may be used separately\n" +
            "under those permissions, but the entire Program remains governed by\n" +
            "this License without regard to the additional permissions.\n" +
            "\n" +
            "  When you convey a copy of a covered work, you may at your option\n" +
            "remove any additional permissions from that copy, or from any part of\n" +
            "it.  (Additional permissions may be written to require their own\n" +
            "removal in certain cases when you modify the work.)  You may place\n" +
            "additional permissions on material, added by you to a covered work,\n" +
            "for which you have or can give appropriate copyright permission.\n" +
            "\n" +
            "  Notwithstanding any other provision of this License, for material you\n" +
            "add to a covered work, you may (if authorized by the copyright holders of\n" +
            "that material) supplement the terms of this License with terms:\n" +
            "\n" +
            "    a) Disclaiming warranty or limiting liability differently from the\n" +
            "    terms of sections 15 and 16 of this License; or\n" +
            "\n" +
            "    b) Requiring preservation of specified reasonable legal notices or\n" +
            "    author attributions in that material or in the Appropriate Legal\n" +
            "    Notices displayed by works containing it; or\n" +
            "\n" +
            "    c) Prohibiting misrepresentation of the origin of that material, or\n" +
            "    requiring that modified versions of such material be marked in\n" +
            "    reasonable ways as different from the original version; or\n" +
            "\n" +
            "    d) Limiting the use for publicity purposes of names of licensors or\n" +
            "    authors of the material; or\n" +
            "\n" +
            "    e) Declining to grant rights under trademark law for use of some\n" +
            "    trade names, trademarks, or service marks; or\n" +
            "\n" +
            "    f) Requiring indemnification of licensors and authors of that\n" +
            "    material by anyone who conveys the material (or modified versions of\n" +
            "    it) with contractual assumptions of liability to the recipient, for\n" +
            "    any liability that these contractual assumptions directly impose on\n" +
            "    those licensors and authors.\n" +
            "\n" +
            "  All other non-permissive additional terms are considered \"further\n" +
            "restrictions\" within the meaning of section 10.  If the Program as you\n" +
            "received it, or any part of it, contains a notice stating that it is\n" +
            "governed by this License along with a term that is a further\n" +
            "restriction, you may remove that term.  If a license document contains\n" +
            "a further restriction but permits relicensing or conveying under this\n" +
            "License, you may add to a covered work material governed by the terms\n" +
            "of that license document, provided that the further restriction does\n" +
            "not survive such relicensing or conveying.\n" +
            "\n" +
            "  If you add terms to a covered work in accord with this section, you\n" +
            "must place, in the relevant source files, a statement of the\n" +
            "additional terms that apply to those files, or a notice indicating\n" +
            "where to find the applicable terms.\n" +
            "\n" +
            "  Additional terms, permissive or non-permissive, may be stated in the\n" +
            "form of a separately written license, or stated as exceptions;\n" +
            "the above requirements apply either way.\n" +
            "\n" +
            "  8. Termination.\n" +
            "\n" +
            "  You may not propagate or modify a covered work except as expressly\n" +
            "provided under this License.  Any attempt otherwise to propagate or\n" +
            "modify it is void, and will automatically terminate your rights under\n" +
            "this License (including any patent licenses granted under the third\n" +
            "paragraph of section 11).\n" +
            "\n" +
            "  However, if you cease all violation of this License, then your\n" +
            "license from a particular copyright holder is reinstated (a)\n" +
            "provisionally, unless and until the copyright holder explicitly and\n" +
            "finally terminates your license, and (b) permanently, if the copyright\n" +
            "holder fails to notify you of the violation by some reasonable means\n" +
            "prior to 60 days after the cessation.\n" +
            "\n" +
            "  Moreover, your license from a particular copyright holder is\n" +
            "reinstated permanently if the copyright holder notifies you of the\n" +
            "violation by some reasonable means, this is the first time you have\n" +
            "received notice of violation of this License (for any work) from that\n" +
            "copyright holder, and you cure the violation prior to 30 days after\n" +
            "your receipt of the notice.\n" +
            "\n" +
            "  Termination of your rights under this section does not terminate the\n" +
            "licenses of parties who have received copies or rights from you under\n" +
            "this License.  If your rights have been terminated and not permanently\n" +
            "reinstated, you do not qualify to receive new licenses for the same\n" +
            "material under section 10.\n" +
            "\n" +
            "  9. Acceptance Not Required for Having Copies.\n" +
            "\n" +
            "  You are not required to accept this License in order to receive or\n" +
            "run a copy of the Program.  Ancillary propagation of a covered work\n" +
            "occurring solely as a consequence of using peer-to-peer transmission\n" +
            "to receive a copy likewise does not require acceptance.  However,\n" +
            "nothing other than this License grants you permission to propagate or\n" +
            "modify any covered work.  These actions infringe copyright if you do\n" +
            "not accept this License.  Therefore, by modifying or propagating a\n" +
            "covered work, you indicate your acceptance of this License to do so.\n" +
            "\n" +
            "  10. Automatic Licensing of Downstream Recipients.\n" +
            "\n" +
            "  Each time you convey a covered work, the recipient automatically\n" +
            "receives a license from the original licensors, to run, modify and\n" +
            "propagate that work, subject to this License.  You are not responsible\n" +
            "for enforcing compliance by third parties with this License.\n" +
            "\n" +
            "  An \"entity transaction\" is a transaction transferring control of an\n" +
            "organization, or substantially all assets of one, or subdividing an\n" +
            "organization, or merging organizations.  If propagation of a covered\n" +
            "work results from an entity transaction, each party to that\n" +
            "transaction who receives a copy of the work also receives whatever\n" +
            "licenses to the work the party's predecessor in interest had or could\n" +
            "give under the previous paragraph, plus a right to possession of the\n" +
            "Corresponding Source of the work from the predecessor in interest, if\n" +
            "the predecessor has it or can get it with reasonable efforts.\n" +
            "\n" +
            "  You may not impose any further restrictions on the exercise of the\n" +
            "rights granted or affirmed under this License.  For example, you may\n" +
            "not impose a license fee, royalty, or other charge for exercise of\n" +
            "rights granted under this License, and you may not initiate litigation\n" +
            "(including a cross-claim or counterclaim in a lawsuit) alleging that\n" +
            "any patent claim is infringed by making, using, selling, offering for\n" +
            "sale, or importing the Program or any portion of it.\n" +
            "\n" +
            "  11. Patents.\n" +
            "\n" +
            "  A \"contributor\" is a copyright holder who authorizes use under this\n" +
            "License of the Program or a work on which the Program is based.  The\n" +
            "work thus licensed is called the contributor's \"contributor version\".\n" +
            "\n" +
            "  A contributor's \"essential patent claims\" are all patent claims\n" +
            "owned or controlled by the contributor, whether already acquired or\n" +
            "hereafter acquired, that would be infringed by some manner, permitted\n" +
            "by this License, of making, using, or selling its contributor version,\n" +
            "but do not include claims that would be infringed only as a\n" +
            "consequence of further modification of the contributor version.  For\n" +
            "purposes of this definition, \"control\" includes the right to grant\n" +
            "patent sublicenses in a manner consistent with the requirements of\n" +
            "this License.\n" +
            "\n" +
            "  Each contributor grants you a non-exclusive, worldwide, royalty-free\n" +
            "patent license under the contributor's essential patent claims, to\n" +
            "make, use, sell, offer for sale, import and otherwise run, modify and\n" +
            "propagate the contents of its contributor version.\n" +
            "\n" +
            "  In the following three paragraphs, a \"patent license\" is any express\n" +
            "agreement or commitment, however denominated, not to enforce a patent\n" +
            "(such as an express permission to practice a patent or covenant not to\n" +
            "sue for patent infringement).  To \"grant\" such a patent license to a\n" +
            "party means to make such an agreement or commitment not to enforce a\n" +
            "patent against the party.\n" +
            "\n" +
            "  If you convey a covered work, knowingly relying on a patent license,\n" +
            "and the Corresponding Source of the work is not available for anyone\n" +
            "to copy, free of charge and under the terms of this License, through a\n" +
            "publicly available network server or other readily accessible means,\n" +
            "then you must either (1) cause the Corresponding Source to be so\n" +
            "available, or (2) arrange to deprive yourself of the benefit of the\n" +
            "patent license for this particular work, or (3) arrange, in a manner\n" +
            "consistent with the requirements of this License, to extend the patent\n" +
            "license to downstream recipients.  \"Knowingly relying\" means you have\n" +
            "actual knowledge that, but for the patent license, your conveying the\n" +
            "covered work in a country, or your recipient's use of the covered work\n" +
            "in a country, would infringe one or more identifiable patents in that\n" +
            "country that you have reason to believe are valid.\n" +
            "\n" +
            "  If, pursuant to or in connection with a single transaction or\n" +
            "arrangement, you convey, or propagate by procuring conveyance of, a\n" +
            "covered work, and grant a patent license to some of the parties\n" +
            "receiving the covered work authorizing them to use, propagate, modify\n" +
            "or convey a specific copy of the covered work, then the patent license\n" +
            "you grant is automatically extended to all recipients of the covered\n" +
            "work and works based on it.\n" +
            "\n" +
            "  A patent license is \"discriminatory\" if it does not include within\n" +
            "the scope of its coverage, prohibits the exercise of, or is\n" +
            "conditioned on the non-exercise of one or more of the rights that are\n" +
            "specifically granted under this License.  You may not convey a covered\n" +
            "work if you are a party to an arrangement with a third party that is\n" +
            "in the business of distributing software, under which you make payment\n" +
            "to the third party based on the extent of your activity of conveying\n" +
            "the work, and under which the third party grants, to any of the\n" +
            "parties who would receive the covered work from you, a discriminatory\n" +
            "patent license (a) in connection with copies of the covered work\n" +
            "conveyed by you (or copies made from those copies), or (b) primarily\n" +
            "for and in connection with specific products or compilations that\n" +
            "contain the covered work, unless you entered into that arrangement,\n" +
            "or that patent license was granted, prior to 28 March 2007.\n" +
            "\n" +
            "  Nothing in this License shall be construed as excluding or limiting\n" +
            "any implied license or other defenses to infringement that may\n" +
            "otherwise be available to you under applicable patent law.\n" +
            "\n" +
            "  12. No Surrender of Others' Freedom.\n" +
            "\n" +
            "  If conditions are imposed on you (whether by court order, agreement or\n" +
            "otherwise) that contradict the conditions of this License, they do not\n" +
            "excuse you from the conditions of this License.  If you cannot convey a\n" +
            "covered work so as to satisfy simultaneously your obligations under this\n" +
            "License and any other pertinent obligations, then as a consequence you may\n" +
            "not convey it at all.  For example, if you agree to terms that obligate you\n" +
            "to collect a royalty for further conveying from those to whom you convey\n" +
            "the Program, the only way you could satisfy both those terms and this\n" +
            "License would be to refrain entirely from conveying the Program.\n" +
            "\n" +
            "  13. Use with the GNU Affero General Public License.\n" +
            "\n" +
            "  Notwithstanding any other provision of this License, you have\n" +
            "permission to link or combine any covered work with a work licensed\n" +
            "under version 3 of the GNU Affero General Public License into a single\n" +
            "combined work, and to convey the resulting work.  The terms of this\n" +
            "License will continue to apply to the part which is the covered work,\n" +
            "but the special requirements of the GNU Affero General Public License,\n" +
            "section 13, concerning interaction through a network will apply to the\n" +
            "combination as such.\n" +
            "\n" +
            "  14. Revised Versions of this License.\n" +
            "\n" +
            "  The Free Software Foundation may publish revised and/or new versions of\n" +
            "the GNU General Public License from time to time.  Such new versions will\n" +
            "be similar in spirit to the present version, but may differ in detail to\n" +
            "address new problems or concerns.\n" +
            "\n" +
            "  Each version is given a distinguishing version number.  If the\n" +
            "Program specifies that a certain numbered version of the GNU General\n" +
            "Public License \"or any later version\" applies to it, you have the\n" +
            "option of following the terms and conditions either of that numbered\n" +
            "version or of any later version published by the Free Software\n" +
            "Foundation.  If the Program does not specify a version number of the\n" +
            "GNU General Public License, you may choose any version ever published\n" +
            "by the Free Software Foundation.\n" +
            "\n" +
            "  If the Program specifies that a proxy can decide which future\n" +
            "versions of the GNU General Public License can be used, that proxy's\n" +
            "public statement of acceptance of a version permanently authorizes you\n" +
            "to choose that version for the Program.\n" +
            "\n" +
            "  Later license versions may give you additional or different\n" +
            "permissions.  However, no additional obligations are imposed on any\n" +
            "author or copyright holder as a result of your choosing to follow a\n" +
            "later version.\n" +
            "\n" +
            "  15. Disclaimer of Warranty.\n" +
            "\n" +
            "  THERE IS NO WARRANTY FOR THE PROGRAM, TO THE EXTENT PERMITTED BY\n" +
            "APPLICABLE LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING THE COPYRIGHT\n" +
            "HOLDERS AND/OR OTHER PARTIES PROVIDE THE PROGRAM \"AS IS\" WITHOUT WARRANTY\n" +
            "OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,\n" +
            "THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR\n" +
            "PURPOSE.  THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE PROGRAM\n" +
            "IS WITH YOU.  SHOULD THE PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF\n" +
            "ALL NECESSARY SERVICING, REPAIR OR CORRECTION.\n" +
            "\n" +
            "  16. Limitation of Liability.\n" +
            "\n" +
            "  IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN WRITING\n" +
            "WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MODIFIES AND/OR CONVEYS\n" +
            "THE PROGRAM AS PERMITTED ABOVE, BE LIABLE TO YOU FOR DAMAGES, INCLUDING ANY\n" +
            "GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE\n" +
            "USE OR INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED TO LOSS OF\n" +
            "DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY YOU OR THIRD\n" +
            "PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY OTHER PROGRAMS),\n" +
            "EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE POSSIBILITY OF\n" +
            "SUCH DAMAGES.\n" +
            "\n" +
            "  17. Interpretation of Sections 15 and 16.\n" +
            "\n" +
            "  If the disclaimer of warranty and limitation of liability provided\n" +
            "above cannot be given local legal effect according to their terms,\n" +
            "reviewing courts shall apply local law that most closely approximates\n" +
            "an absolute waiver of all civil liability in connection with the\n" +
            "Program, unless a warranty or assumption of liability accompanies a\n" +
            "copy of the Program in return for a fee.\n" +
            "\nEND OF TERMS AND CONDITIONS";

    public static final Map<Integer, String> licensesText = Map.of(
            0, "Apache License\n" +
                    "Version 2.0, January 2004\n" +
                    "http://www.apache.org/licenses/\n\n" +
                    "TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION\n\n" +
                    "1. Definitions.\n\n" +
                    "\"License\" shall mean the terms and conditions for use, reproduction, and distribution as defined by Sections 1 through 9 of this document.\n\n" +
                    "\"Licensor\" shall mean the copyright owner or entity authorized by the copyright owner that is granting the License.\n\n" +
                    "\"Legal Entity\" shall mean the union of the acting entity and all other entities that control, are controlled by, or are under common control with that entity. For the purposes of this definition, \"control\" means (i) the power, direct or indirect, to cause the direction or management of such entity, whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii) beneficial ownership of such entity.\n\n" +
                    "\"You\" (or \"Your\") shall mean an individual or Legal Entity exercising permissions granted by this License.\n\n" +
                    "\"Source\" form shall mean the preferred form for making modifications, including but not limited to software source code, documentation source, and configuration files.\n\n" +
                    "\"Object\" form shall mean any form resulting from mechanical transformation or translation of a Source form, including but not limited to compiled object code, generated documentation, and conversions to other media types.\n\n" +
                    "\"Work\" shall mean the work of authorship, whether in Source or Object form, made available under the License, as indicated by a copyright notice that is included in or attached to the work (an example is provided in the Appendix below).\n\n" +
                    "\"Derivative Works\" shall mean any work, whether in Source or Object form, that is based on (or derived from) the Work and for which the editorial revisions, annotations, elaborations, or other modifications represent, as a whole, an original work of authorship. For the purposes of this License, Derivative Works shall not include works that remain separable from, or merely link (or bind by name) to the interfaces of, the Work and Derivative Works thereof.\n\n" +
                    "\"Contribution\" shall mean any work of authorship, including the original version of the Work and any modifications or additions to that Work or Derivative Works thereof, that is intentionally submitted to Licensor for inclusion in the Work by the copyright owner or by an individual or Legal Entity authorized to submit on behalf of the copyright owner. For the purposes of this definition, \"submitted\" means any form of electronic, verbal, or written communication sent to the Licensor or its representatives, including but not limited to communication on electronic mailing lists, source code control systems, and issue tracking systems that are managed by, or on behalf of, the Licensor for the purpose of discussing and improving the Work, but excluding communication that is conspicuously marked or otherwise designated in writing by the copyright owner as \"Not a Contribution.\"\n\n" +
                    "\"Contributor\" shall mean Licensor and any individual or Legal Entity on behalf of whom a Contribution has been received by Licensor and subsequently incorporated within the Work.\n\n" +
                    "2. Grant of Copyright License. Subject to the terms and conditions of this License, each Contributor hereby grants to You a perpetual, worldwide, non-exclusive, no-charge, royalty-free, irrevocable copyright license to reproduce, prepare Derivative Works of, publicly display, publicly perform, sublicense, and distribute the Work and such Derivative Works in Source or Object form.\n\n" +
                    "3. Grant of Patent License. Subject to the terms and conditions of this License, each Contributor hereby grants to You a perpetual, worldwide, non-exclusive, no-charge, royalty-free, irrevocable (except as stated in this section) patent license to make, have made, use, offer to sell, sell, import, and otherwise transfer the Work, where such license applies only to those patent claims licensable by such Contributor that are necessarily infringed by their Contribution(s) alone or by combination of their Contribution(s) with the Work to which such Contribution(s) was submitted. If You institute patent litigation against any entity (including a cross-claim or counterclaim in a lawsuit) alleging that the Work or a Contribution incorporated within the Work constitutes direct or contributory patent infringement, then any patent licenses granted to You under this License for that Work shall terminate as of the date such litigation is filed.\n\n" +
                    "4. Redistribution. You may reproduce and distribute copies of the Work or Derivative Works thereof in any medium, with or without modifications, and in Source or Object form, provided that You meet the following conditions:\n\n" +
                    "    You must give any other recipients of the Work or Derivative Works a copy of this License; and\n" +
                    "    You must cause any modified files to carry prominent notices stating that You changed the files; and\n" +
                    "    You must retain, in the Source form of any Derivative Works that You distribute, all copyright, patent, trademark, and attribution notices from the Source form of the Work, excluding those notices that do not pertain to any part of the Derivative Works; and\n" +
                    "    If the Work includes a \"NOTICE\" text file as part of its distribution, then any Derivative Works that You distribute must include a readable copy of the attribution notices contained within such NOTICE file, excluding those notices that do not pertain to any part of the Derivative Works, in at least one of the following places: within a NOTICE text file distributed as part of the Derivative Works; within the Source form or documentation, if provided along with the Derivative Works; or, within a display generated by the Derivative Works, if and wherever such third-party notices normally appear. The contents of the NOTICE file are for informational purposes only and do not modify the License. You may add Your own attribution notices within Derivative Works that You distribute, alongside or as an addendum to the NOTICE text from the Work, provided that such additional attribution notices cannot be construed as modifying the License.\n\n" +
                    "You may add Your own copyright statement to Your modifications and may provide additional or different license terms and conditions for use, reproduction, or distribution of Your modifications, or for any such Derivative Works as a whole, provided Your use, reproduction, and distribution of the Work otherwise complies with the conditions stated in this License.\n\n" +
                    "5. Submission of Contributions. Unless You explicitly state otherwise, any Contribution intentionally submitted for inclusion in the Work by You to the Licensor shall be under the terms and conditions of this License, without any additional terms or conditions. Notwithstanding the above, nothing herein shall supersede or modify the terms of any separate license agreement you may have executed with Licensor regarding such Contributions.\n\n" +
                    "6. Trademarks. This License does not grant permission to use the trade names, trademarks, service marks, or product names of the Licensor, except as required for reasonable and customary use in describing the origin of the Work and reproducing the content of the NOTICE file.\n\n" +
                    "7. Disclaimer of Warranty. Unless required by applicable law or agreed to in writing, Licensor provides the Work (and each Contributor provides its Contributions) on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied, including, without limitation, any warranties or conditions of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are solely responsible for determining the appropriateness of using or redistributing the Work and assume any risks associated with Your exercise of permissions under this License.\n\n" +
                    "8. Limitation of Liability. In no event and under no legal theory, whether in tort (including negligence), contract, or otherwise, unless required by applicable law (such as deliberate and grossly negligent acts) or agreed to in writing, shall any Contributor be liable to You for damages, including any direct, indirect, special, incidental, or consequential damages of any character arising as a result of this License or out of the use or inability to use the Work (including but not limited to damages for loss of goodwill, work stoppage, computer failure or malfunction, or any and all other commercial damages or losses), even if such Contributor has been advised of the possibility of such damages.\n\n" +
                    "9. Accepting Warranty or Additional Liability. While redistributing the Work or Derivative Works thereof, You may choose to offer, and charge a fee for, acceptance of support, warranty, indemnity, or other liability obligations and/or rights consistent with this License. However, in accepting such obligations, You may act only on Your own behalf and on Your sole responsibility, not on behalf of any other Contributor, and only if You agree to indemnify, defend, and hold each Contributor harmless for any liability incurred by, or claims asserted against, such Contributor by reason of your accepting any such warranty or additional liability.\n\n" +
                    "END OF TERMS AND CONDITIONS\n",
            1, "Apache License\n" +
                    "Version 2.0, January 2004\n" +
                    "http://www.apache.org/licenses/\n\n" +
                    "TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION\n\n" +
                    "1. Definitions.\n\n" +
                    "\"License\" shall mean the terms and conditions for use, reproduction, and distribution as defined by Sections 1 through 9 of this document.\n\n" +
                    "\"Licensor\" shall mean the copyright owner or entity authorized by the copyright owner that is granting the License.\n\n" +
                    "\"Legal Entity\" shall mean the union of the acting entity and all other entities that control, are controlled by, or are under common control with that entity. For the purposes of this definition, \"control\" means (i) the power, direct or indirect, to cause the direction or management of such entity, whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii) beneficial ownership of such entity.\n\n" +
                    "\"You\" (or \"Your\") shall mean an individual or Legal Entity exercising permissions granted by this License.\n\n" +
                    "\"Source\" form shall mean the preferred form for making modifications, including but not limited to software source code, documentation source, and configuration files.\n\n" +
                    "\"Object\" form shall mean any form resulting from mechanical transformation or translation of a Source form, including but not limited to compiled object code, generated documentation, and conversions to other media types.\n\n" +
                    "\"Work\" shall mean the work of authorship, whether in Source or Object form, made available under the License, as indicated by a copyright notice that is included in or attached to the work (an example is provided in the Appendix below).\n\n" +
                    "\"Derivative Works\" shall mean any work, whether in Source or Object form, that is based on (or derived from) the Work and for which the editorial revisions, annotations, elaborations, or other modifications represent, as a whole, an original work of authorship. For the purposes of this License, Derivative Works shall not include works that remain separable from, or merely link (or bind by name) to the interfaces of, the Work and Derivative Works thereof.\n\n" +
                    "\"Contribution\" shall mean any work of authorship, including the original version of the Work and any modifications or additions to that Work or Derivative Works thereof, that is intentionally submitted to Licensor for inclusion in the Work by the copyright owner or by an individual or Legal Entity authorized to submit on behalf of the copyright owner. For the purposes of this definition, \"submitted\" means any form of electronic, verbal, or written communication sent to the Licensor or its representatives, including but not limited to communication on electronic mailing lists, source code control systems, and issue tracking systems that are managed by, or on behalf of, the Licensor for the purpose of discussing and improving the Work, but excluding communication that is conspicuously marked or otherwise designated in writing by the copyright owner as \"Not a Contribution.\"\n\n" +
                    "\"Contributor\" shall mean Licensor and any individual or Legal Entity on behalf of whom a Contribution has been received by Licensor and subsequently incorporated within the Work.\n\n" +
                    "2. Grant of Copyright License. Subject to the terms and conditions of this License, each Contributor hereby grants to You a perpetual, worldwide, non-exclusive, no-charge, royalty-free, irrevocable copyright license to reproduce, prepare Derivative Works of, publicly display, publicly perform, sublicense, and distribute the Work and such Derivative Works in Source or Object form.\n\n" +
                    "3. Grant of Patent License. Subject to the terms and conditions of this License, each Contributor hereby grants to You a perpetual, worldwide, non-exclusive, no-charge, royalty-free, irrevocable (except as stated in this section) patent license to make, have made, use, offer to sell, sell, import, and otherwise transfer the Work, where such license applies only to those patent claims licensable by such Contributor that are necessarily infringed by their Contribution(s) alone or by combination of their Contribution(s) with the Work to which such Contribution(s) was submitted. If You institute patent litigation against any entity (including a cross-claim or counterclaim in a lawsuit) alleging that the Work or a Contribution incorporated within the Work constitutes direct or contributory patent infringement, then any patent licenses granted to You under this License for that Work shall terminate as of the date such litigation is filed.\n\n" +
                    "4. Redistribution. You may reproduce and distribute copies of the Work or Derivative Works thereof in any medium, with or without modifications, and in Source or Object form, provided that You meet the following conditions:\n\n" +
                    "    You must give any other recipients of the Work or Derivative Works a copy of this License; and\n" +
                    "    You must cause any modified files to carry prominent notices stating that You changed the files; and\n" +
                    "    You must retain, in the Source form of any Derivative Works that You distribute, all copyright, patent, trademark, and attribution notices from the Source form of the Work, excluding those notices that do not pertain to any part of the Derivative Works; and\n" +
                    "    If the Work includes a \"NOTICE\" text file as part of its distribution, then any Derivative Works that You distribute must include a readable copy of the attribution notices contained within such NOTICE file, excluding those notices that do not pertain to any part of the Derivative Works, in at least one of the following places: within a NOTICE text file distributed as part of the Derivative Works; within the Source form or documentation, if provided along with the Derivative Works; or, within a display generated by the Derivative Works, if and wherever such third-party notices normally appear. The contents of the NOTICE file are for informational purposes only and do not modify the License. You may add Your own attribution notices within Derivative Works that You distribute, alongside or as an addendum to the NOTICE text from the Work, provided that such additional attribution notices cannot be construed as modifying the License.\n\n" +
                    "You may add Your own copyright statement to Your modifications and may provide additional or different license terms and conditions for use, reproduction, or distribution of Your modifications, or for any such Derivative Works as a whole, provided Your use, reproduction, and distribution of the Work otherwise complies with the conditions stated in this License.\n\n" +
                    "5. Submission of Contributions. Unless You explicitly state otherwise, any Contribution intentionally submitted for inclusion in the Work by You to the Licensor shall be under the terms and conditions of this License, without any additional terms or conditions. Notwithstanding the above, nothing herein shall supersede or modify the terms of any separate license agreement you may have executed with Licensor regarding such Contributions.\n\n" +
                    "6. Trademarks. This License does not grant permission to use the trade names, trademarks, service marks, or product names of the Licensor, except as required for reasonable and customary use in describing the origin of the Work and reproducing the content of the NOTICE file.\n\n" +
                    "7. Disclaimer of Warranty. Unless required by applicable law or agreed to in writing, Licensor provides the Work (and each Contributor provides its Contributions) on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied, including, without limitation, any warranties or conditions of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are solely responsible for determining the appropriateness of using or redistributing the Work and assume any risks associated with Your exercise of permissions under this License.\n\n" +
                    "8. Limitation of Liability. In no event and under no legal theory, whether in tort (including negligence), contract, or otherwise, unless required by applicable law (such as deliberate and grossly negligent acts) or agreed to in writing, shall any Contributor be liable to You for damages, including any direct, indirect, special, incidental, or consequential damages of any character arising as a result of this License or out of the use or inability to use the Work (including but not limited to damages for loss of goodwill, work stoppage, computer failure or malfunction, or any and all other commercial damages or losses), even if such Contributor has been advised of the possibility of such damages.\n\n" +
                    "9. Accepting Warranty or Additional Liability. While redistributing the Work or Derivative Works thereof, You may choose to offer, and charge a fee for, acceptance of support, warranty, indemnity, or other liability obligations and/or rights consistent with this License. However, in accepting such obligations, You may act only on Your own behalf and on Your sole responsibility, not on behalf of any other Contributor, and only if You agree to indemnify, defend, and hold each Contributor harmless for any liability incurred by, or claims asserted against, such Contributor by reason of your accepting any such warranty or additional liability.\n\n" +
                    "END OF TERMS AND CONDITIONS\n",
            2, "Apache License\n" +
                    "Version 2.0, January 2004\n" +
                    "http://www.apache.org/licenses/\n\n" +
                    "TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION\n\n" +
                    "1. Definitions.\n\n" +
                    "\"License\" shall mean the terms and conditions for use, reproduction, and distribution as defined by Sections 1 through 9 of this document.\n\n" +
                    "\"Licensor\" shall mean the copyright owner or entity authorized by the copyright owner that is granting the License.\n\n" +
                    "\"Legal Entity\" shall mean the union of the acting entity and all other entities that control, are controlled by, or are under common control with that entity. For the purposes of this definition, \"control\" means (i) the power, direct or indirect, to cause the direction or management of such entity, whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii) beneficial ownership of such entity.\n\n" +
                    "\"You\" (or \"Your\") shall mean an individual or Legal Entity exercising permissions granted by this License.\n\n" +
                    "\"Source\" form shall mean the preferred form for making modifications, including but not limited to software source code, documentation source, and configuration files.\n\n" +
                    "\"Object\" form shall mean any form resulting from mechanical transformation or translation of a Source form, including but not limited to compiled object code, generated documentation, and conversions to other media types.\n\n" +
                    "\"Work\" shall mean the work of authorship, whether in Source or Object form, made available under the License, as indicated by a copyright notice that is included in or attached to the work (an example is provided in the Appendix below).\n\n" +
                    "\"Derivative Works\" shall mean any work, whether in Source or Object form, that is based on (or derived from) the Work and for which the editorial revisions, annotations, elaborations, or other modifications represent, as a whole, an original work of authorship. For the purposes of this License, Derivative Works shall not include works that remain separable from, or merely link (or bind by name) to the interfaces of, the Work and Derivative Works thereof.\n\n" +
                    "\"Contribution\" shall mean any work of authorship, including the original version of the Work and any modifications or additions to that Work or Derivative Works thereof, that is intentionally submitted to Licensor for inclusion in the Work by the copyright owner or by an individual or Legal Entity authorized to submit on behalf of the copyright owner. For the purposes of this definition, \"submitted\" means any form of electronic, verbal, or written communication sent to the Licensor or its representatives, including but not limited to communication on electronic mailing lists, source code control systems, and issue tracking systems that are managed by, or on behalf of, the Licensor for the purpose of discussing and improving the Work, but excluding communication that is conspicuously marked or otherwise designated in writing by the copyright owner as \"Not a Contribution.\"\n\n" +
                    "\"Contributor\" shall mean Licensor and any individual or Legal Entity on behalf of whom a Contribution has been received by Licensor and subsequently incorporated within the Work.\n\n" +
                    "2. Grant of Copyright License. Subject to the terms and conditions of this License, each Contributor hereby grants to You a perpetual, worldwide, non-exclusive, no-charge, royalty-free, irrevocable copyright license to reproduce, prepare Derivative Works of, publicly display, publicly perform, sublicense, and distribute the Work and such Derivative Works in Source or Object form.\n\n" +
                    "3. Grant of Patent License. Subject to the terms and conditions of this License, each Contributor hereby grants to You a perpetual, worldwide, non-exclusive, no-charge, royalty-free, irrevocable (except as stated in this section) patent license to make, have made, use, offer to sell, sell, import, and otherwise transfer the Work, where such license applies only to those patent claims licensable by such Contributor that are necessarily infringed by their Contribution(s) alone or by combination of their Contribution(s) with the Work to which such Contribution(s) was submitted. If You institute patent litigation against any entity (including a cross-claim or counterclaim in a lawsuit) alleging that the Work or a Contribution incorporated within the Work constitutes direct or contributory patent infringement, then any patent licenses granted to You under this License for that Work shall terminate as of the date such litigation is filed.\n\n" +
                    "4. Redistribution. You may reproduce and distribute copies of the Work or Derivative Works thereof in any medium, with or without modifications, and in Source or Object form, provided that You meet the following conditions:\n\n" +
                    "    You must give any other recipients of the Work or Derivative Works a copy of this License; and\n" +
                    "    You must cause any modified files to carry prominent notices stating that You changed the files; and\n" +
                    "    You must retain, in the Source form of any Derivative Works that You distribute, all copyright, patent, trademark, and attribution notices from the Source form of the Work, excluding those notices that do not pertain to any part of the Derivative Works; and\n" +
                    "    If the Work includes a \"NOTICE\" text file as part of its distribution, then any Derivative Works that You distribute must include a readable copy of the attribution notices contained within such NOTICE file, excluding those notices that do not pertain to any part of the Derivative Works, in at least one of the following places: within a NOTICE text file distributed as part of the Derivative Works; within the Source form or documentation, if provided along with the Derivative Works; or, within a display generated by the Derivative Works, if and wherever such third-party notices normally appear. The contents of the NOTICE file are for informational purposes only and do not modify the License. You may add Your own attribution notices within Derivative Works that You distribute, alongside or as an addendum to the NOTICE text from the Work, provided that such additional attribution notices cannot be construed as modifying the License.\n\n" +
                    "You may add Your own copyright statement to Your modifications and may provide additional or different license terms and conditions for use, reproduction, or distribution of Your modifications, or for any such Derivative Works as a whole, provided Your use, reproduction, and distribution of the Work otherwise complies with the conditions stated in this License.\n\n" +
                    "5. Submission of Contributions. Unless You explicitly state otherwise, any Contribution intentionally submitted for inclusion in the Work by You to the Licensor shall be under the terms and conditions of this License, without any additional terms or conditions. Notwithstanding the above, nothing herein shall supersede or modify the terms of any separate license agreement you may have executed with Licensor regarding such Contributions.\n\n" +
                    "6. Trademarks. This License does not grant permission to use the trade names, trademarks, service marks, or product names of the Licensor, except as required for reasonable and customary use in describing the origin of the Work and reproducing the content of the NOTICE file.\n\n" +
                    "7. Disclaimer of Warranty. Unless required by applicable law or agreed to in writing, Licensor provides the Work (and each Contributor provides its Contributions) on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied, including, without limitation, any warranties or conditions of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are solely responsible for determining the appropriateness of using or redistributing the Work and assume any risks associated with Your exercise of permissions under this License.\n\n" +
                    "8. Limitation of Liability. In no event and under no legal theory, whether in tort (including negligence), contract, or otherwise, unless required by applicable law (such as deliberate and grossly negligent acts) or agreed to in writing, shall any Contributor be liable to You for damages, including any direct, indirect, special, incidental, or consequential damages of any character arising as a result of this License or out of the use or inability to use the Work (including but not limited to damages for loss of goodwill, work stoppage, computer failure or malfunction, or any and all other commercial damages or losses), even if such Contributor has been advised of the possibility of such damages.\n\n" +
                    "9. Accepting Warranty or Additional Liability. While redistributing the Work or Derivative Works thereof, You may choose to offer, and charge a fee for, acceptance of support, warranty, indemnity, or other liability obligations and/or rights consistent with this License. However, in accepting such obligations, You may act only on Your own behalf and on Your sole responsibility, not on behalf of any other Contributor, and only if You agree to indemnify, defend, and hold each Contributor harmless for any liability incurred by, or claims asserted against, such Contributor by reason of your accepting any such warranty or additional liability.\n\n" +
                    "END OF TERMS AND CONDITIONS\n"
    );

    public static final List<String> partyUrls = List.of(
            "https://github.com/material-components/material-components-android",
            "https://github.com/androidx/androidx",
            "https://pictogrammers.com/library/mdi/"
    );

    public static final List<License> licenses = List.of(
            new License("Material Components for Android", "Google, Inc. under ALv2", licensesText.get(0), partyUrls.get(0)),
            new License("AndroidX", "Android Open Source Project under ALv2", licensesText.get(1), partyUrls.get(1)),
            new License("Material Design Icons", "Pictogrammers under ALv2", licensesText.get(2), partyUrls.get(2))
    );

    /**
     * Show full license for the app or a library
     * @param context      Context
     * @param licenseIndex Index of the license in the licenses list. Use -1 to show app license
     */
    public static void showFullLicense(Context context, int licenseIndex)
    {
        // Show app license if index is -1
        if (licenseIndex == -1)
        {
            new MaterialAlertDialogBuilder(context)
                    .setTitle("PocketBox")
                    .setMessage(appLicense)
                    .setPositiveButton("DONE", null)
                    .show();
        }
        else
        {
            new MaterialAlertDialogBuilder(context)
                    .setTitle(licenses.get(licenseIndex).getName())
                    .setMessage(licenses.get(licenseIndex).getLicense())
                    .setPositiveButton("DONE", null)
                    .setNegativeButton("OPEN WEBSITE", (dialog, which) -> {

                        // Visit page in browser
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(licenses.get(licenseIndex).getUrl()));
                        context.startActivity(browserIntent);
                    })
                    .show();
        }
    }
}
