import "@/styles/globals.css";
import type { AppProps } from "next/app";
import Head from "next/head";
import { Heebo } from "next/font/google";
import Layout from "../components/Layout";
import { ThemeProvider, createTheme } from "@mui/material/styles";

const rubik = Heebo({ subsets: ["latin"] });
const darkTheme = createTheme({
   palette: {
      mode: "dark",
   },
});

export default function App({ Component, pageProps }: AppProps) {
   return (
      <>
         <Head>
            <title>Alexis Jennings Portfolio</title>
            <meta
               name="viewport"
               content="minimum-scale=1, initial-scale=1, width=device-width"
            />
         </Head>
         <ThemeProvider theme={darkTheme}>
            <Layout>
               <Component {...pageProps} />
            </Layout>
         </ThemeProvider>
      </>
   );
}
