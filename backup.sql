PGDMP                         {           my_db    10.23    10.23     �
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                        1262    24618    my_db    DATABASE     �   CREATE DATABASE my_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Ukrainian_Ukraine.1251' LC_CTYPE = 'Ukrainian_Ukraine.1251';
    DROP DATABASE my_db;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false                       0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    24619    commands_table    TABLE     #  CREATE TABLE public.commands_table (
    technology_id integer NOT NULL,
    callback_data character varying(255) NOT NULL,
    command_syntax character varying(455),
    command_meaning character varying(255),
    example character varying(255),
    id integer,
    techology_id integer
);
 "   DROP TABLE public.commands_table;
       public         postgres    false    3            �            1259    24625    technologies_table    TABLE        CREATE TABLE public.technologies_table (
    technology_id integer NOT NULL,
    technology character varying(100) NOT NULL
);
 &   DROP TABLE public.technologies_table;
       public         postgres    false    3            �            1259    24628    users_data_table    TABLE     �   CREATE TABLE public.users_data_table (
    chat_id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    registered_at timestamp(6) without time zone,
    username character varying(255)
);
 $   DROP TABLE public.users_data_table;
       public         bestuser    false    3            �
          0    24619    commands_table 
   TABLE DATA               �   COPY public.commands_table (technology_id, callback_data, command_syntax, command_meaning, example, id, techology_id) FROM stdin;
    public       postgres    false    196   �       �
          0    24625    technologies_table 
   TABLE DATA               G   COPY public.technologies_table (technology_id, technology) FROM stdin;
    public       postgres    false    197   )       �
          0    24628    users_data_table 
   TABLE DATA               c   COPY public.users_data_table (chat_id, first_name, last_name, registered_at, username) FROM stdin;
    public       bestuser    false    198   [       w
           2606    24635 "   commands_table commands_table_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY public.commands_table
    ADD CONSTRAINT commands_table_pkey PRIMARY KEY (callback_data);
 L   ALTER TABLE ONLY public.commands_table DROP CONSTRAINT commands_table_pkey;
       public         postgres    false    196            y
           2606    24637 *   technologies_table technologies_table_pkey 
   CONSTRAINT     s   ALTER TABLE ONLY public.technologies_table
    ADD CONSTRAINT technologies_table_pkey PRIMARY KEY (technology_id);
 T   ALTER TABLE ONLY public.technologies_table DROP CONSTRAINT technologies_table_pkey;
       public         postgres    false    197            {
           2606    24639 &   users_data_table users_data_table_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.users_data_table
    ADD CONSTRAINT users_data_table_pkey PRIMARY KEY (chat_id);
 P   ALTER TABLE ONLY public.users_data_table DROP CONSTRAINT users_data_table_pkey;
       public         bestuser    false    198            |
           2606    24640 0   commands_table commands_table_technology_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.commands_table
    ADD CONSTRAINT commands_table_technology_id_fkey FOREIGN KEY (technology_id) REFERENCES public.technologies_table(technology_id) NOT VALID;
 Z   ALTER TABLE ONLY public.commands_table DROP CONSTRAINT commands_table_technology_id_fkey;
       public       postgres    false    2681    196    197            }
           2606    24645 *   commands_table fk2eg6u48nk3ix2e5ti7omcjcob    FK CONSTRAINT     �   ALTER TABLE ONLY public.commands_table
    ADD CONSTRAINT fk2eg6u48nk3ix2e5ti7omcjcob FOREIGN KEY (id) REFERENCES public.technologies_table(technology_id);
 T   ALTER TABLE ONLY public.commands_table DROP CONSTRAINT fk2eg6u48nk3ix2e5ti7omcjcob;
       public       postgres    false    196    197    2681            ~
           2606    24650 *   commands_table fkm2h0jl8niol2ixm96irqlehd6    FK CONSTRAINT     �   ALTER TABLE ONLY public.commands_table
    ADD CONSTRAINT fkm2h0jl8niol2ixm96irqlehd6 FOREIGN KEY (techology_id) REFERENCES public.technologies_table(technology_id);
 T   ALTER TABLE ONLY public.commands_table DROP CONSTRAINT fkm2h0jl8niol2ixm96irqlehd6;
       public       postgres    false    196    2681    197            �
   -  x��Xin�8�휂ȟڀ��i
�5�k+m ��̠��,1ITI��g�4s�9�<.��%ngI�z|�Ə�h���M�Y!X-C��X�$$�!�&�#	f6�f�'������i^!�F���,���D$�v�M���{#/	�NI�-�(��'ʢ�A�ؼ������c4R�1P�{i�=E��ŪQߋB�E���,�#�x�}�DD�R���4��TL\�_���z��I��|"�$AD�pW[��N�/��/�\��f|��Һ�ح�ą��	�0�Y�^_]^^z�{�ߞW�+�5e�T� �������pĜ����(;{k�G�q�e.�*京~))���1���9�\+Ol�(e�ǜ�L;���"M ZfW���n��ֆ����j��z>!��R4L��`��6#ѡ���$�n���M(�O�t|e����ſDnG.g�D������(��6��	o�ak�x�&G��`Rm�Y"�r�=�e2�M!�k��1����Y���l5׊�t�Ϗ���Y��W/�0:CY� *
]m��dt�h�H	����^����ΰr��)�y�z�e)�����W��c��#d��f�3�l�[w��S=� ��&Y/ogЯ��7�m�d��G�b��g�-}t4��l��>:3t7Z�?:���w?��t�^� l�	$6@d��
�=bt�櫊Lv���{&�|h僮�rq�K��>0-����$��� 8�� �G����QYҌ�8��`�$����	�F���ӽ�d��9���i�f5���A�;H���Z�	Z�@�
*=D����5^82e���������z@�Ň����������ox�&gO�S�&��h=]�$KYw�|�_�	.��n��;�0�(�~[�^B��ɺ�4��:-��C��/޼阽�^����akLľD�$��Z;"�]D���bB�H�&����P��7Q�ؗ1B�6����(	 Ѱq"KY?#H���v=P�� �w�.1	A��/�#�z��<D���g�8�!{�X�~��vI�I��E���Fe����#
W�2'Wh��
|��}�3��F�B����P�l�,>�1�8��."<t1��'�TW1��R��J�rRC��+�o'�d��/�N�؜[/Q/F�c���9m�(�d�U���"Ҧ������d&� @H	����r˼�5r���-�c
q��N����� )s �6g�����D~�NrbcV��M��v�72��[�0��i���e#(/O���(_�f�����O�ۖ�\�n�Q� �nD�/�Y;�l�Ҫ��v�
�A (�y��������R�*��25s���e�
�}�³�5��fL��2͢��d}���A��z5(�Zc'ɨV%�w�@3qn,��"�aB����n2���u%� l$t�XkZ�~�|�\����������š�a�(#Z�P�05�jix�I
���(d>����1�.�z����OP����חu@�Q_��O���9$����X�ٹ����9�pӮͅW2����� ����ˋd8Gӕ�0`�vz4���rk@�3�T��'�7�����b~�����n�,MG$$�_��K@��@)C%��#g6�s�r�-М��l�i(o�y-ta$���axz�2H T0��^��u��s:���9
�����Ѵ�]u�j��M��䩆���!�W>��pA?��ė-�Z�g�R7ؑ�#�\�	v��8N#���sڞ2.��E�X�j�-�(�k�D9��	��|�a��+:J3m�?99���N      �
   "   x�3�t��2�t�w�v�2������� G�      �
   T   x�I ��909896351	Сергей	Рыжков	2023-05-15 17:27:51.841	hd0uk3n
\.


��|     