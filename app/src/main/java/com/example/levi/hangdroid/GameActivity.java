package com.example.levi.hangdroid;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity
    {
    //initializing objects used throughout
    String theWord = "";
    int goodLetterCount = 0;
    int badLetterCount = 0;
    int points = 0;
    boolean win;
    MediaPlayer newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        }

    //displays the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
        {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
        }

    //sets options for clicking in the action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
        {
        switch (item.getItemId())
            {
            case R.id.action_main_menu:

                //takes you to the main menu activity
                Intent mainMenu = new Intent(this, MainActivity.class);
                startActivity(mainMenu);

                return true;

            case R.id.action_new_single_player:

                //takes you to the single player activity
                Intent newGame = new Intent(this, GameActivity.class);
                startActivity(newGame);

                return true;

            case R.id.action_new_multi_player:

                //takes you to the multi player activity
                Intent newMultiGame = new Intent(this, MultiPlayerActivity.class);
                startActivity(newMultiGame);

                return true;

            case R.id.action_scores:

                //takes you to the scores activity
                Intent scores = new Intent(this, ScoresActivity.class);
                startActivity(scores);

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

            }
        }


    //I set this up to release the media player if this activity is ended for whatever reason
    @Override
    public void onPause()
        {
        super.onPause();
        newGame.release();
        }

    //I have read that using the onResume is better for the typical onCreate items for functionality,
    //so I tried it , and it seems to work well.
    @Override
    public void onResume()
        {
        super.onResume();

        //pull layout from xml file
        setContentView(R.layout.activity_game);

        //sets a new word
        //runs setWord method
        setWord();

        //sets up the media player to play the music clip
        newGame = MediaPlayer.create(this, R.raw.newgame);

        //plays the music clip
        newGame.start();

        //releases the music clip once it finishes playing so it doesn't hog memory
        newGame.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
        public void onCompletion(MediaPlayer mp)
            {
            newGame.release(); // finish current activity
            }
        });
        }

    //instantiates the text views for the word programmatically, makes it so I can add
    //different length words in the future
    private void createTextViews(String word)
        {
        //retrieve layout
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutLetters);

        //create needed number of spaces for the entered word
        for (int i = 0; i < word.length(); i++)
            {
            //calls the layout of a single letter space as
            //many times as necessary until i=word.length
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.single_letter, null);
            layout.addView(textView);
            }
        }

    //creates sll of the buttons for the alphabet and assigns them the letter value for the guess
    public void letterPressed(View view)
        {
        String letter = "";

        switch (view.getId())
            {
            case R.id.buttonA:
                letter = "A";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonB:
                letter = "B";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonC:
                letter = "C";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonD:
                letter = "D";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonE:
                letter = "E";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonF:
                letter = "F";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonG:
                letter = "G";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonH:
                letter = "H";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonI:
                letter = "I";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonJ:
                letter = "J";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonK:
                letter = "K";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonL:
                letter = "L";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonM:
                letter = "M";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonN:
                letter = "N";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonO:
                letter = "O";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonP:
                letter = "P";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonQ:
                letter = "Q";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonR:
                letter = "R";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonS:
                letter = "S";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonT:
                letter = "T";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonU:
                letter = "U";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonV:
                letter = "V";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonW:
                letter = "W";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonX:
                letter = "X";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonY:
                letter = "Y";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonZ:
                letter = "Z";
                view.setVisibility(View.GONE);
                break;
            }

        //log to see what the letter guess was
        Log.d("MYLOG", "The letter is: " + letter);

        //run checkLetter method below
        checkLetter(letter);
        }

    public void checkLetter(String letter)
        {
        char aLetter = letter.charAt(0);

        boolean letterGuessed = false;

        //for loop to check for correct or wrong letters
        for (int i = 0; i < theWord.length(); i++)
            {
            //runs the guessed letter against every letter in the word, checking if it matches
            if (theWord.charAt(i) == aLetter)
                {
                letterGuessed = true;

                //logs the correctly guessed letter
                Log.d("MYLOG", "Letter Found: " + aLetter);

                //keeps track of how many letters are guessed correctly
                goodLetterCount++;

                //displays the correctly guessed letter in the location that matches the word
                showLetter(i, aLetter);
                }
            }

        if (!letterGuessed)
            {
            //logs the incorrect guess
            Log.d("MYLOG", "Letter NOT found " + aLetter);

            //increases the count of bad guesses
            badLetterCount++;

            //displays the incorrect guess on the screen
            previousLetter(Character.toString(aLetter));
            }

        Log.d("MYLOG", "Check for win: " + goodLetterCount + " " + theWord.length());

        //checks to see if you have won
        if (goodLetterCount == theWord.length())
            {
            //displays victory text
            Toast.makeText(this, "If one wants to truly pwn, one must pwn... in ALL games!", Toast.LENGTH_SHORT).show();

            //adds to the total points for wins
            points++;

            //determines what part of the gameOver method to run
            win = true;

            //runs game over method
            gameOver();
            }
        }

    public void previousLetter(String guessedLetter)
        {
        //references the location of the wrong letter field
        TextView textView = (TextView) findViewById(R.id.textViewWrong);

        //references the string of previously incorrect guesses
        String previousLetters = textView.getText().toString();

        //logs the incorrect guess
        Log.d("MYLOG", "Guessed Letter: " + guessedLetter + " " + badLetterCount);

        //begins adding the incorrect letters to the string
        if (badLetterCount > 1)
            {
            //adds the bad letter to the previousLetters string
            textView.setText(previousLetters + " " + guessedLetter);
            }
        else
            {
            textView.setText(guessedLetter);
            }
        //selects what image to display based on how many wrong answers there are
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        if (badLetterCount == 1)
            imageView.setImageResource(R.drawable.hangdroid_1);
        if (badLetterCount == 2)
            imageView.setImageResource(R.drawable.hangdroid_2);
        if (badLetterCount == 3)
            imageView.setImageResource(R.drawable.hangdroid_3);
        if (badLetterCount == 4)
            imageView.setImageResource(R.drawable.hangdroid_4);
        if (badLetterCount == 5)
            imageView.setImageResource(R.drawable.hangdroid_5);
        if (badLetterCount > 5)
            {
            //you lose toast, references an internet show that I love named PurePwnage
            Toast.makeText(this, "LOL - gg Kyle!", Toast.LENGTH_LONG).show();

            //boolean value that determines what gameOver activity to run
            win = false;

            //runs game over method
            gameOver();
            }
        }

    public void showLetter(int position, char letterGuessed)
        {
        //reference the location of "layoutLetters" in our linear layout
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutLetters);

        //references the location of the text view within the layout
        TextView textView = (TextView) layout.getChildAt(position);

        //fills in the space containing the correct letter guess with the letter
        textView.setText(Character.toString(letterGuessed));
        }

    public void gameOver()
        {
        //if you lose
        if (!win)
            {
            //creates the intent
            Intent intent = new Intent(this, GameOverLoseActivity.class);

            //sends the points data with the intent
            intent.putExtra("PointsID", points);

            //starts the activity defined by the intent above
            startActivity(intent);

            //calls method to clear the game for a new game
            clearScreen();

            //new word
            setWord();
            }
        //if you win
        if (win)
            {
            //creates the intent
            Intent intent = new Intent(this, GameOverWinActivity.class);

            //sends the points data with the intent
            intent.putExtra("PointsID", points);

            //starts the activity defined by the intent above
            startActivity(intent);

            //calls method to clear the game for a new game
            clearScreen();

            //new word
            setWord();
            }
        }

    private void setWord()
        {
        //all of the possible four letter words for the single player game
        String words = "ABET ABLE ABLY ABUT ACED ACES ACHE ACID ACME ACNE ACRE ACTS ADDS ADZE AFAR AFRO AGAR AGED AGES AGOG AGUE AHEM AHOY AIDE AIDS AILS AIMS AIRS AIRY AJAR AKIN ALAS ALES ALGA ALLY ALMS ALOE ALPS ALSO ALTO ALUM AMBO AMEN AMID AMOK AMPS ANAL ANEW ANON ANTE ANTS ANUS APED APES APEX APPS AQUA ARCH ARCS AREA ARIA ARID ARKS ARMS ARMY ARTS ARTY ASHY ASKS ASPS ATOM ATOP AUNT AURA AUTO AVER AVID AVOW AWAY AWED AWES AWLS AWNS AWOL AWRY AXED AXEL AXES AXIS AXLE AXON AYES BAAS BABE BABY BACK BADE BAGS BAHT BAIL BAIT BAKE BALD BALE BALK BALL BALM BAND BANE BANG BANK BANS BARB BARD BARE BARF BARK BARN BARS BASE BASH BASK BASS BATH BATS BAUD BAWD BAWL BAYS BEAD BEAK BEAM BEAN BEAR BEAT BEAU BEDS BEEF BEEN BEEP BEER BEES BEET BEGS BELL BELT BEND BENT BERM BEST BETA BETS BEVY BIAS BIBS BIDE BIDS BIKE BILE BILK BILL BIND BINS BIOS BIRD BIRR BITE BITS BITT BLAB BLAH BLAT BLEB BLED BLEW BLIP BLOB BLOG BLOT BLOW BLUE BLUR BOAR BOAS BOAT BOBS BODE BODY BOGS BOIL BOLD BOLL BOLT BOMB BOND BONE BONK BONY BOOB BOOK BOOM BOON BOOR BOOS BOOT BOPS BORE BORN BOSS BOTH BOTS BOUT BOWL BOWS BOXY BOYS BRAD BRAG BRAN BRAS BRAT BRAY BRED BREW BRIE BRIM BRIS BROW BUBO BUCK BUDS BUFF BUGS BULB BULK BULL BUMP BUMS BUNK BUNS BUNT BUOY BURL BURN BURP BURR BURS BURY BUSH BUSK BUST BUSY BUTS BUTT BUYS BUZZ BYES BYTE CABS CADS CAFE CAGE CAKE CALF CALL CALM CAME CAMP CAMS CANE CANS CANT CAPE CAPS CARD CARE CARP CARS CART CASE CASH CASK CAST CATS CAVE CAWS CECA CEDE CEDI CEES CELL CENT CHAD CHAP CHAR CHAT CHEF CHEW CHIC CHIN CHIP CHIS CHOP CHOW CHUB CHUG CHUM CITE CITY CLAD CLAN CLAP CLAW CLAY CLEF CLIP CLOD CLOG CLOT CLUB CLUE COAL COAT COAX COBS COCK CODA CODE CODS COED COGS COIF COIL COIN COLA COLD COLT COMA COMB COME CONE CONK CONS COOK COOL COOP COOS COOT COPE COPS COPY CORD CORE CORK CORM CORN COST COTS COUP COVE COWL COWS COZY CRAB CRAG CRAM CRAP CREW CRIB CROP CROW CRUD CRUX CUBE CUBS CUDS CUED CUES CUFF CULL CULT CUPS CURB CURD CURE CURL CURS CURT CUSP CUSS CUTE CUTS CWMS CYAN CYST CZAR DABS DADO DADS DAFT DAME DAMN DAMP DAMS DANK DARE DARK DARN DART DASH DATA DATE DAUB DAWN DAYS DAZE DEAD DEAF DEAL DEAN DEAR DEBT DECK DEED DEEM DEEP DEER DEFT DEFY DELE DELI DELL DEME DEMO DEMY DENE DENS DENT DENY DERM DESK DEVA DEWS DEWY DHOW DIAL DIBS DICE DIED DIES DIET DIGS DILL DIME DIMS DINE DING DINS DIPS DIRE DIRT DISC DISH DISK DITZ DIVA DIVE DOCK DODO DOER DOES DOFF DOGE DOGS DOLE DOLL DOLT DOME DONE DONS DOOM DOOR DOPE DORK DORM DOSE DOTE DOTH DOTS DOVE DOWN DOZE DOZY DRAB DRAG DRAM DRAW DREG DREW DRIP DROP DRUG DRUM DUAL DUBS DUCK DUCT DUDE DUDS DUEL DUES DUET DUFF DUKE DULL DULY DUMB DUMP DUNE DUNG DUNK DUOS DUPE DUSK DUST DUTY DYED DYER DYES DYNE EACH EARL EARN EARS EASE EAST EASY EATS EAVE EBBS ECHO ECRU EDDY EDGE EDGY EDIT EELS EELY EFFS EGGS EGOS EKED EKES ELKS ELLS ELMS ELSE EMIR EMIT EMUS ENDS ENOL ENVY EONS EPIC ERAS ERGO ETAS ETCH EURO EVEN EVER EVES EVIL EWER EWES EXAM EXES EXIT EXON EXPO EYED EYES FACE FACT FADE FADS FAIL FAIN FAIR FAKE FALL FAME FANG FANS FARE FARM FAST FATE FATS FAUN FAUX FAWN FAZE FEAR FEAT FEDS FEED FEEL FEES FEET FELL FELT FEND FENS FERN FETA FEUD FIBS FIGS FILE FILL FILM FIND FINE FINK FINS FIRE FIRM FIRS FISH FIST FITS FIVE FIZZ FLAB FLAG FLAN FLAP FLAT FLAW FLAX FLAY FLEA FLED FLEE FLEW FLEX FLIP FLIT FLOE FLOG FLOP FLOW FLUB FLUE FLUX FOAL FOAM FOBS FOCI FOES FOGS FOGY FOIL FOLD FOLK FOND FONT FOOD FOOL FOOT FORA FORE FORK FORM FORT FOUL FOUR FOWL FOXY FRAY FREE FRET FROG FROM FUEL FULL FUME FUMY FUND FUNK FURS FURY FUSE FUSS FUZZ GABS GAFF GAGA GAGE GAGS GAIN GAIT GALA GALE GALL GALS GAME GANG GAPE GAPS GARB GASH GASP GATE GAVE GAWK GAZE GEAR GEEK GEES GELD GELS GEMS GENE GENT GERM GETS GIFT GIGS GILD GILL GILT GIMP GINS GIRD GIRL GIST GIVE GLAD GLEE GLEN GLIA GLIB GLOB GLOW GLUE GLUM GLUT GNAT GNAW GNUS GOAD GOAL GOAT GOBS GODS GOER GOES GOJI GOLD GOLF GONE GONG GOOD GOOF GOON GOOP GORE GORY GOSH GOTH GOUT GOWN GRAB GRAM GRAY GREW GREY GRID GRIM GRIN GRIP GRIT GROW GRUB GUCK GUFF GULF GULL GULP GUMS GUNK GUNS GURU GUSH GUST GUTS GUYS GYMS GYPS GYRE GYRO HACK HAGS HAIL HAIR HALF HALL HALO HALT HAMS HAND HANG HAPS HARD HARE HARK HARM HARP HASH HASP HATE HATH HATS HAUL HAVE HAWK HAWS HAYS HAZE HAZY HEAD HEAL HEAP HEAR HEAT HECK HEED HEEL HEIR HELD HELL HELM HELP HEMS HENS HERB HERD HERE HERO HERS HEWN HEWS HICK HIDE HIGH HIKE HILL HILT HIND HINT HIPS HIRE HISS HITS HIVE HOAR HOAX HOBO HOED HOER HOES HOGS HOLD HOLE HOLY HOME HONE HONK HOOD HOOF HOOK HOOP HOOT HOPE HOPS HORN HOSE HOST HOTS HOUR HOWL HUBS HUED HUES HUFF HUGE HUGS HULA HULK HULL HUMP HUMS HUNG HUNK HUNT HURL HURT HUSH HUSK HUTS HYMN HYPE HYPO IBEX IBIS ICED ICES ICKY ICON IDEA IDES IDLE IDLY IDOL IFFY ILEA ILLS IMAM IMPS INCH INKS INKY INNS INTO IONS IOTA IRES IRIS IRKS IRON ISLE ITCH ITEM JABS JACK JADE JAGS JAIL JAMB JAMS JARS JAVA JAWS JAYS JAZZ JEAN JEEP JEER JELL JERK JEST JETS JIBE JIGS JILT JINX JIVE JOBS JOCK JOGS JOIN JOKE JOLT JOSH JOTS JOWL JOYS JUDO JUGS JUKE JUMP JUNK JURY JUST JUTE JUTS KALE KAYS KEEL KEEN KEEP KEGS KELP KEPT KERN KEYS KICK KIDS KILL KILN KILT KINA KIND KING KINK KIPS KISS KITE KITS KIWI KNEE KNEW KNIT KNOB KNOT KNOW KOOK KUDU KYAK KYAT LABS LACE LACK LACY LADE LADS LADY LAGS LAID LAIN LAIR LAKE LAMB LAME LAMP LAND LANE LANK LAPS LARD LARK LASH LASS LAST LATE LAUD LAVA LAVE LAWN LAWS LAYS LAZE LAZY LEAD LEAF LEAK LEAN LEAP LEAS LEEK LEER LEFT LEGS LEND LENS LENT LESS LEST LETS LEVS LEVY LEWD LIAR LICE LICK LIDS LIED LIEN LIES LIEU LIFE LIFT LIKE LILY LIMB LIME LIMN LIMO LIMP LIMY LINE LINK LINT LION LIPS LIRA LIRE LISP LIST LITE LIVE LOAD LOAF LOAM LOAN LOBE LOBS LOCH LOCI LOCK LOCO LODE LOFT LOGO LOGS LOIN LOLL LONE LONG LOOK LOOM LOON LOOP LOOS LOOT LOPE LOPS LORD LORE LOSE LOSS LOST LOTI LOTS LOUD LOUT LOVE LOWS LUAU LUBE LUCK LUFF LUGE LUGS LULL LUMP LUNG LURE LURK LUSH LUST LUTE LYNX LYRE MACE MADE MAGE MAGI MAID MAIL MAIM MAIN MAKE MALE MALL MALT MAMA MANE MANY MAPS MARE MARK MARL MARS MART MASH MASK MASS MAST MATE MATH MATS MATT MAUL MAWS MAYO MAYS MAZE MEAD MEAL MEAN MEAT MEEK MEET MELD MELT MEMO MEND MENU MEOW MERE MESA MESH MESS MEWS MICA MICE MIDI MIFF MILD MILE MILK MILL MILS MIME MIND MINE MINI MINK MINT MINX MIRE MISS MIST MITE MITT MOAN MOAT MOBS MOCK MODE MODS MOLD MOLE MOLT MOMS MONK MOOD MOON MOOR MOOS MOOT MOPE MOPS MORE MOSS MOST MOTH MOVE MOWN MOWS MUCH MUCK MUFF MUGS MULE MULL MUMS MUON MURK MUSE MUSH MUSK MUST MUTE MUTT MYNA MYTH NABS NAGS NAIL NAME NAPE NAPS NAVE NAVY NAYS NEAP NEAR NEAT NECK NEED NEON NERD NEST NETS NEVI NEWS NEWT NEXT NIBS NICE NICK NIGH NINE NIPS NITS NOBS NODE NODS NOEL NONE NOON NOPE NORM NOSE NOSY NOTE NOUN NOVA NUDE NUKE NULL NUMB NUNS NUTS OAFS OAKS OARS OATH OATS OBEY OBOE ODDS ODES ODOR OFFS OGLE OGRE OHMS OILS OILY OINK OKAY OKRA OLEO OMEN OMIT ONCE ONES ONLY ONTO ONUS ONYX OOHS OOPS OOZE OOZY OPAL OPEN OPTS ORAL ORBS ORCA ORES OUCH OURS OUST OUTS OUZO OVAL OVEN OVER OVUM OWED OWES OWLS OWNS OXEN PACE PACK PACT PADS PAGE PAID PAIL PAIN PAIR PALE PALL PALM PALS PANE PANG PANS PANT PAPA PARE PARK PARS PART PASS PAST PATE PATH PATS PAVE PAWN PAWS PAYS PEAK PEAL PEAR PEAS PEAT PECK PEEK PEEL PEEP PEER PEGS PELF PELT PEND PENS PENT PEON PEPS PERK PERM PERT PESO PEST PETS PEWS PHIS PICK PIED PIES PIGS PIKE PILE PILI PILL PIMP PINE PING PINK PINS PINT PIPE PIPS PITA PITH PITS PITY PLAN PLAY PLEA PLED PLOD PLOP PLOT PLOW PLOY PLUG PLUM PLUS POCK PODS POEM POET POGO POKE POKY POLE POLL POLO POMP POND PONY POOH POOL POOP POOR POPE POPS PORE PORK PORN PORT POSE POSH POST POSY POTS POUF POUR POUT PRAM PRAY PREP PREY PRIM PROD PROM PROP PROS PROW PUBS PUCE PUCK PUFF PUGS PUKE PULL PULP PUMA PUMP PUNK PUNS PUNT PUNY PUPA PUPS PURE PURR PUSH PUTS PUTT PYRE QUAD QUAY QUID QUIP QUIT QUIZ RACE RACK RACY RAFT RAGE RAGS RAID RAIL RAIN RAKE RAMP RAMS RAND RANG RANK RANT RAPE RAPS RAPT RARE RASH RASP RATE RATS RAVE RAYS RAZE RAZZ READ REAL REAM REAP REAR REDO REDS REED REEF REEK REEL REFS REIN RELY REND RENT REPO REST REVS RIAL RIBS RICE RICH RICK RIDE RIDS RIEL RIFE RIFF RIFT RIGS RILE RILL RIME RIMS RIND RING RINK RIOT RIPE RIPS RISE RISK RITE ROAD ROAM ROAN ROAR ROBE ROBS ROCK RODE RODS ROES ROIL ROLE ROLL ROMP ROOD ROOF ROOK ROOM ROOT ROPE ROPY ROSE ROSY ROTE ROTS ROUT ROVE ROWS RUBS RUBY RUDE RUED RUES RUFF RUGS RUIN RULE RUMS RUNE RUNG RUNS RUNT RUSE RUSH RUST RUTS SACK SACS SAFE SAGA SAGE SAGS SAID SAIL SAKE SALE SALT SAME SAND SANE SANG SANK SAPS SARI SASH SASS SATE SAVE SAWN SAWS SAYS SCAB SCAM SCAN SCAR SCAT SCOT SCUD SCUM SEAL SEAM SEAR SEAS SEAT SECT SEED SEEK SEEM SEEN SEEP SEER SEES SEND SENT SERA SERE SERF SETA SETS SEWN SEWS SEXT SEXY SHAH SHAM SHED SHIM SHIN SHIP SHOE SHOO SHOP SHOT SHOW SHUN SHUT SICK SIDE SIFT SIGH SIGN SILK SILL SILO SILT SINE SING SINK SINS SIPS SIRE SIRS SITE SITS SIZE SKEW SKID SKIM SKIN SKIP SKIS SKIT SLAB SLAM SLAP SLAT SLAW SLAY SLED SLEW SLID SLIM SLIP SLIT SLOB SLOE SLOP SLOT SLOW SLUG SLUM SLUR SMIT SMOG SMUG SMUT SNAG SNAP SNIP SNIT SNOB SNOG SNOT SNOW SNUB SNUG SOAK SOAP SOAR SOBS SOCK SODA SODS SOFA SOFT SOIL SOLD SOLE SOLO SOME SONG SONS SOON SOOT SOPS SORE SORT SOTS SOUL SOUP SOUR SOWN SOWS SOYA SPAM SPAN SPAR SPAS SPAT SPAY SPED SPIN SPIT SPOT SPRY SPUD SPUN SPUR STAB STAG STAR STAT STAY STEM STEP STEW STIR STOP STOW STUB STUD STUN STYX SUBS SUCH SUCK SUDS SUED SUES SUET SUIT SULK SUMO SUMP SUMS SUNG SUNK SUNS SURE SURF SWAB SWAG SWAM SWAN SWAP SWAT SWAY SWIG SWIM SWUM SYNC TABS TACK TACO TACT TAGS TAIL TAKA TAKE TALA TALC TALE TALK TALL TAME TAMP TAMS TANK TANS TAPE TAPS TARE TARN TARO TARP TARS TART TASK TAUT TAXA TAXI TEAK TEAL TEAM TEAR TEAS TECH TEED TEEM TEEN TEES TELL TEND TENS TENT TERM TERN TEST TEXT THAN THAT THAW THEE THEM THEN THEY THIN THIS THOU THRU THUD THUG THUS TICK TICS TIDE TIDY TIED TIER TIES TIFF TIKE TILE TILL TILT TIME TINE TING TINS TINT TINY TIPS TIRE TOAD TOED TOES TOFU TOGA TOIL TOLD TOLL TOMB TOME TONE TONG TONS TOOK TOOL TOOT TOPS TORE TORN TORT TOSS TOTE TOTS TOUR TOUT TOWN TOWS TOYS TRAM TRAP TRAY TREE TREK TRIM TRIO TRIP TROD TROT TROY TRUE TSAR TUBA TUBE TUBS TUCK TUFA TUFF TUFT TUGS TUMS TUNA TUNE TURF TURN TUSK TUTU TWIG TWIN TWIT TWOS TYKE TYPE TYPO TYRO UGHS UGLY ULNA UMPS UNDO UNIT UNTO UPON UREA URGE URIC URNS USED USER USES UVEA VAIN VALE VAMP VANE VANS VARY VASE VAST VATS VATU VEAL VEER VEIL VEIN VEND VENT VERB VERY VEST VETO VETS VIAL VIBE VICE VIED VIES VIEW VILE VINE VISA VISE VOID VOLE VOLT VOTE VOWS WACK WADE WADS WAFT WAGE WAGS WAIF WAIL WAIT WAKE WALK WALL WAND WANE WANT WARD WARE WARM WARN WARP WARS WART WARY WASH WASP WATT WAVE WAVY WAXY WAYS WEAK WEAN WEAR WEBS WEDS WEED WEEK WEEN WEEP WEIR WELD WELL WELT WEND WENT WEPT WERE WEST WETS WHAM WHAT WHEN WHET WHEW WHEY WHIM WHIP WHIR WHIZ WHOA WHOM WHOP WICK WIDE WIFE WIGS WILD WILE WILL WILT WILY WIMP WIND WINE WING WINK WINS WIPE WIRE WIRY WISE WISH WISP WITH WITS WOAD WOES WOKE WOKS WOLF WOMB WONT WOOD WOOF WOOL WOOS WORD WORE WORK WORM WORN WORT WOVE WOWS WRAP WREN WRIT WYES YACK YAGI YAKS YAMS YANG YANK YAPS YARD YARE YARN YAUP YAWL YAWN YAWP YAWS YEAH YEAN YEAR YEAS YELD YELL YELP YENS YETI YEUK YEWS YINS YIPS YOGA YOGH YOGI YOKE YOLK YOND YONI YORE YOUR YOWL YOYO YUAN YUCK YUKS YULE YURT YWIS ZAGS ZANY ZAPS ZARF ZEAL ZEBU ZEDS ZEES ZENS ZERO ZEST ZETA ZIGS ZINC ZINE ZING ZIPS ZITI ZITS ZOIC ZONE ZONK ZOOM ZOON ZOOS ZULU";
        //splits the words using the space
        String[] wordsArray = words.split(" ");

        //shows log of how many words there are available
        Log.d("MYLOG", "Number of words: " + wordsArray.length);

        //picks a random number within the length of the wordsArray
        int theNumber = (int) (Math.random() * wordsArray.length);
        Log.d("MYLOG", "Random Number: " + theNumber);

        //assigns the word based on it's location in the array by the random number
        theWord = wordsArray[theNumber];

        //logs the word
        Log.d("MYLOG", "The Word is: " + theWord);

        //runs method to create the text views programmatically
        createTextViews(theWord);
        }

    public void clearScreen()
        {
        //references the values stored in "textViewWrong"
        TextView textView = (TextView) findViewById(R.id.textViewWrong);

        //resets the values in "textViewWrong"
        textView.setText(" ");

        //resets counters for correct and incorrect guesses
        badLetterCount = 0;
        goodLetterCount = 0;

        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutLetters);

        for (int i = 0; i < layout.getChildCount(); i++)
            {
            TextView childTextView = (TextView) layout.getChildAt(i);
            childTextView.setText("_");
            }
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);
        }
    }
