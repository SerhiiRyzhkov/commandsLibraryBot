PGDMP         	                {           my_db    10.23    10.23     �
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �
           1262    16393    my_db    DATABASE     �   CREATE DATABASE my_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Ukrainian_Ukraine.1251' LC_CTYPE = 'Ukrainian_Ukraine.1251';
    DROP DATABASE my_db;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �
           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false                        0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    16408    commands_table    TABLE     �   CREATE TABLE public.commands_table (
    technology_id integer NOT NULL,
    tg_command character varying(255) NOT NULL,
    command_syntax character varying(455),
    command_meaning character varying(255),
    example character varying(255)
);
 "   DROP TABLE public.commands_table;
       public         postgres    false    3            �            1259    16403    technologies_table    TABLE     �   CREATE TABLE public.technologies_table (
    id integer NOT NULL,
    technology character varying(100) NOT NULL,
    tg_command character varying(100) NOT NULL
);
 &   DROP TABLE public.technologies_table;
       public         postgres    false    3            �            1259    16395    users_data_table    TABLE     �   CREATE TABLE public.users_data_table (
    chat_id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    registered_at timestamp(6) without time zone,
    username character varying(255)
);
 $   DROP TABLE public.users_data_table;
       public         bestuser    false    3            �
          0    16408    commands_table 
   TABLE DATA               m   COPY public.commands_table (technology_id, tg_command, command_syntax, command_meaning, example) FROM stdin;
    public       postgres    false    198   M       �
          0    16403    technologies_table 
   TABLE DATA               H   COPY public.technologies_table (id, technology, tg_command) FROM stdin;
    public       postgres    false    197   {       �
          0    16395    users_data_table 
   TABLE DATA               c   COPY public.users_data_table (chat_id, first_name, last_name, registered_at, username) FROM stdin;
    public       bestuser    false    196   �       {
           2606    16415 "   commands_table commands_table_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.commands_table
    ADD CONSTRAINT commands_table_pkey PRIMARY KEY (tg_command);
 L   ALTER TABLE ONLY public.commands_table DROP CONSTRAINT commands_table_pkey;
       public         postgres    false    198            y
           2606    16407 *   technologies_table technologies_table_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.technologies_table
    ADD CONSTRAINT technologies_table_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.technologies_table DROP CONSTRAINT technologies_table_pkey;
       public         postgres    false    197            w
           2606    16402 &   users_data_table users_data_table_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.users_data_table
    ADD CONSTRAINT users_data_table_pkey PRIMARY KEY (chat_id);
 P   ALTER TABLE ONLY public.users_data_table DROP CONSTRAINT users_data_table_pkey;
       public         bestuser    false    196            |
           2606    16416 0   commands_table commands_table_technology_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.commands_table
    ADD CONSTRAINT commands_table_technology_id_fkey FOREIGN KEY (technology_id) REFERENCES public.technologies_table(id) NOT VALID;
 Z   ALTER TABLE ONLY public.commands_table DROP CONSTRAINT commands_table_technology_id_fkey;
       public       postgres    false    197    198    2681            �
     x��X�n�6�v���Mm�v�4���Jk��6t0*�c�D���x`O�ۓ��Ae�i�&���|��\5.�?b榼�WVː>!�%	Ivȧ��H��Eqve1�^y�:������r�% ��G�D$�v�M�7�{#/	�IE���(��'ʢ�*�l�N��[��1��V�pZUZ�L�|�X5#�{QH�h�Sߚ���^�=��2�b/M!*n.o.�ݼEɎ$��Q!&�Xg[+��&�H��A�"��w���+v���R�K��G\���7����^㷗�deoQ5�KՖ�*�;����O�yUsP5ql6_t���\�"�r]���;�b�	���9�\+��\�2�c�uB �d�&s�BM��l�h�-��8�z�$7�w�T����?`��o3��>��x vSO�E��?�\��(��ݳ׍�%r�8r9���t&�pvDY�4y�-f�Hx��KEc9z`4m�d����v����r���o��b�L4�ӥ�X��t5�25ci�σ��Y��W/�0l���$�U&J:3���"	�J�b��^�Z?2�W��c��)�y�:�e)�j@�W�=����j���Ř�p��Y|p]�!��$��x��^n_�������H\m��5E���naϬ[�䗏��VÏ�����G����N��[��&A�� X�,4�F�t�:��C��S�@e�-��6Z��l�!A>�S�zjp�PA�< +�#��/8,�N�%͘��� �����]s<Al4{�U���O�����ǔK��k��n�9�������?�ޮnh�bO
(�>�\_5kc�pd�V��q?ı�U��f������j�~t�Mbb��B�}��ȹ�'+S)ko���篿!]�y���b��>oj��'�����&W�Bu0?�X��͛�9�#��� ��5GC"���M��5.vD���n=9���Q�;=��u:�eP�]�~2��_Pp�bO�~�9��nF�`.��6=P�� �l:_b����G���Y��C/��q�S���8����P��V�J	0���A��G�1�D��z1)y}��}�S�*AӆB��݅�f[�]����Nvᡋ�o<9���R3��J�E<�[JG�].�=�Nb���[L��8�����^�\�7��Y���B��,R��T���<אf<T�ray�;o˼�5�KK�1��K����? e��,�N�L׺YC�?"?c'�fm�Q.H+V�3i�k����<���a�W! �Q��˸/���p�:�u���VJW�?�g7�` �>R�r�*�
>��./V��,����p���P�cn�b�-�u$�W��Y�t���Y����o�-(�s�t�\[��H��H��v�ǚ��T�"�pCSe����B`��z6�@�M��� "�Ez$.���ϥ2@�ys���+\-ӯ����a��Do�Ħ�˴Fm.��ͬ��PuP&�gX���pj���ho���]9� ��� G��(*]��4��H?A;`��^W ���Dm�Js�"�-ʱ|�]k$�]ꑦ�2�K�dV���m��m�Up�A����Y�c�;���r� U��'D��F�O?*e����p6Y�O�%�� XO�K����)E��J�tp��(�!x�X�W�5���t�/���y2���QL�/��LO�P׵`Kf𴆞�:s��K6���>`4m(m�:B�q�2ojJX\ː�c��h@\�/{`�XO�%��Q!�ER���	
���8N#����鞝����      �
   4   x�3�t���O�,�2�t�w�v��O�O�N-�2����/.������ �Y
�      �
   T   x�I ��909896351	Сергей	Рыжков	2023-05-08 16:21:36.565	hd0uk3n
\.


��}     