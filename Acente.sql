PGDMP      9                |            Acente    15.7    16.3 5    4           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            5           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            6           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            7           1262    25047    Acente    DATABASE     }   CREATE DATABASE "Acente" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE "Acente";
                postgres    false            �            1259    25058    hotel    TABLE     �   CREATE TABLE public.hotel (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    address text NOT NULL,
    email character varying(100),
    phone character varying(20),
    stars integer,
    features text
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    25057    hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.hotel_id_seq;
       public          postgres    false    217            8           0    0    hotel_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.hotel_id_seq OWNED BY public.hotel.id;
          public          postgres    false    216            �            1259    25079    pension_type    TABLE     t   CREATE TABLE public.pension_type (
    id integer NOT NULL,
    hotel_id integer,
    type character varying(50)
);
     DROP TABLE public.pension_type;
       public         heap    postgres    false            �            1259    25078    pension_type_id_seq    SEQUENCE     �   CREATE SEQUENCE public.pension_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.pension_type_id_seq;
       public          postgres    false    221            9           0    0    pension_type_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.pension_type_id_seq OWNED BY public.pension_type.id;
          public          postgres    false    220            �            1259    25113    reservation    TABLE     �  CREATE TABLE public.reservation (
    id integer NOT NULL,
    room_id integer,
    checkin_date date,
    checkout_date date,
    guest_count_adult integer,
    guest_count_child integer,
    total_price numeric(10,2),
    customer_name character varying(100),
    customer_phone character varying(20),
    customer_email character varying(100),
    customer_id_number character varying(20)
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    25112    reservation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.reservation_id_seq;
       public          postgres    false    225            :           0    0    reservation_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;
          public          postgres    false    224            �            1259    25091    room    TABLE     �  CREATE TABLE public.room (
    id integer NOT NULL,
    hotel_id integer,
    season_id integer,
    pension_type_id integer,
    type character varying(50),
    price_per_night_adult numeric(10,2),
    price_per_night_child numeric(10,2),
    stock integer,
    bed_count integer,
    size_sqm integer,
    has_tv boolean,
    has_minibar boolean,
    has_game_console boolean,
    has_safe boolean,
    has_projector boolean
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    25090    room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.room_id_seq;
       public          postgres    false    223            ;           0    0    room_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.room_id_seq OWNED BY public.room.id;
          public          postgres    false    222            �            1259    25067    season    TABLE     v   CREATE TABLE public.season (
    id integer NOT NULL,
    hotel_id integer,
    start_date date,
    end_date date
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    25066    season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.season_id_seq;
       public          postgres    false    219            <           0    0    season_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.season_id_seq OWNED BY public.season.id;
          public          postgres    false    218            �            1259    25049    user    TABLE     �   CREATE TABLE public."user" (
    id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    role character varying(20) NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    25048    user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.user_id_seq;
       public          postgres    false    215            =           0    0    user_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;
          public          postgres    false    214                       2604    25061    hotel id    DEFAULT     d   ALTER TABLE ONLY public.hotel ALTER COLUMN id SET DEFAULT nextval('public.hotel_id_seq'::regclass);
 7   ALTER TABLE public.hotel ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217            �           2604    25082    pension_type id    DEFAULT     r   ALTER TABLE ONLY public.pension_type ALTER COLUMN id SET DEFAULT nextval('public.pension_type_id_seq'::regclass);
 >   ALTER TABLE public.pension_type ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    220    221            �           2604    25116    reservation id    DEFAULT     p   ALTER TABLE ONLY public.reservation ALTER COLUMN id SET DEFAULT nextval('public.reservation_id_seq'::regclass);
 =   ALTER TABLE public.reservation ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    225    225            �           2604    25094    room id    DEFAULT     b   ALTER TABLE ONLY public.room ALTER COLUMN id SET DEFAULT nextval('public.room_id_seq'::regclass);
 6   ALTER TABLE public.room ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    223    223            �           2604    25070 	   season id    DEFAULT     f   ALTER TABLE ONLY public.season ALTER COLUMN id SET DEFAULT nextval('public.season_id_seq'::regclass);
 8   ALTER TABLE public.season ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    219    219            ~           2604    25052    user id    DEFAULT     d   ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);
 8   ALTER TABLE public."user" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    215    215            )          0    25058    hotel 
   TABLE DATA           Q   COPY public.hotel (id, name, address, email, phone, stars, features) FROM stdin;
    public          postgres    false    217   �<       -          0    25079    pension_type 
   TABLE DATA           :   COPY public.pension_type (id, hotel_id, type) FROM stdin;
    public          postgres    false    221   a=       1          0    25113    reservation 
   TABLE DATA           �   COPY public.reservation (id, room_id, checkin_date, checkout_date, guest_count_adult, guest_count_child, total_price, customer_name, customer_phone, customer_email, customer_id_number) FROM stdin;
    public          postgres    false    225   �=       /          0    25091    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, season_id, pension_type_id, type, price_per_night_adult, price_per_night_child, stock, bed_count, size_sqm, has_tv, has_minibar, has_game_console, has_safe, has_projector) FROM stdin;
    public          postgres    false    223   a>       +          0    25067    season 
   TABLE DATA           D   COPY public.season (id, hotel_id, start_date, end_date) FROM stdin;
    public          postgres    false    219   �>       '          0    25049    user 
   TABLE DATA           >   COPY public."user" (id, username, password, role) FROM stdin;
    public          postgres    false    215   �>       >           0    0    hotel_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.hotel_id_seq', 2, true);
          public          postgres    false    216            ?           0    0    pension_type_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.pension_type_id_seq', 9, true);
          public          postgres    false    220            @           0    0    reservation_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.reservation_id_seq', 5, true);
          public          postgres    false    224            A           0    0    room_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.room_id_seq', 3, true);
          public          postgres    false    222            B           0    0    season_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.season_id_seq', 1, true);
          public          postgres    false    218            C           0    0    user_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.user_id_seq', 5, true);
          public          postgres    false    214            �           2606    25065    hotel hotel_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    217            �           2606    25084    pension_type pension_type_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.pension_type
    ADD CONSTRAINT pension_type_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.pension_type DROP CONSTRAINT pension_type_pkey;
       public            postgres    false    221            �           2606    25118    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    225            �           2606    25096    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    223            �           2606    25072    season season_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    219            �           2606    25054    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    215            �           2606    25056    user user_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_username_key;
       public            postgres    false    215            �           2606    25085 '   pension_type pension_type_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pension_type
    ADD CONSTRAINT pension_type_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(id);
 Q   ALTER TABLE ONLY public.pension_type DROP CONSTRAINT pension_type_hotel_id_fkey;
       public          postgres    false    217    3209    221            �           2606    25119 $   reservation reservation_room_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(id);
 N   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_room_id_fkey;
       public          postgres    false    3215    225    223            �           2606    25097    room room_hotel_id_fkey    FK CONSTRAINT     w   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(id);
 A   ALTER TABLE ONLY public.room DROP CONSTRAINT room_hotel_id_fkey;
       public          postgres    false    217    223    3209            �           2606    25107    room room_pension_type_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pension_type_id_fkey FOREIGN KEY (pension_type_id) REFERENCES public.pension_type(id);
 H   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pension_type_id_fkey;
       public          postgres    false    3213    223    221            �           2606    25102    room room_season_id_fkey    FK CONSTRAINT     z   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_season_id_fkey FOREIGN KEY (season_id) REFERENCES public.season(id);
 B   ALTER TABLE ONLY public.room DROP CONSTRAINT room_season_id_fkey;
       public          postgres    false    223    219    3211            �           2606    25073    season season_hotel_id_fkey    FK CONSTRAINT     {   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(id);
 E   ALTER TABLE ONLY public.season DROP CONSTRAINT season_hotel_id_fkey;
       public          postgres    false    3209    217    219            )   �   x�]�M�0���s���6�Ht��	�T)Ҁ4)mR<\A�@�t��d�%��p8�����G]+\^����w'ٴ������^�I9kp�\��M�9��;=*�����l�((fy.8�6e�{����Fp�!L"���%(���r�����x�"2��F.���XD�      -   �   x�3�4��))JT�H-R8:?�R�%1#3��(�&d�OIT�N�(K�)9���(�����W�Y���e
�L,:�I�(�������X���ep����Q�H,�<�\��4'G!�(5%��+F��� U/�      1   ^   x�M�;
�0Cg�.�'C/�t�V(��cM��!d���`�ؔ����d!��9�,]%Vf���#�v���?j�.u�c��#��1N�""/�!u      /   Q   x�3�4C�Ҽ��"���̒TNCS=N(e�&@��@�˘��Ǆ383/='U!(??��B���zҠzb���� :��      +      x�3�4�4202�50"8�И+F��� Q��      '   -   x�3�LL����442���L9K2��B�E��y�9\1z\\\ %�y     