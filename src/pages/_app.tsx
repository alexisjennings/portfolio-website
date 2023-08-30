import "@/styles/globals.css";
import type { AppProps } from "next/app";
import Head from "next/head";
import Layout from "../components/Layout";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import { CssBaseline } from "@mui/material";
import "@fontsource-variable/jost";

const darkTheme = createTheme({
   palette: {
      mode: "dark",
      primary: {
         main: "#b9dbd9",
      },
      secondary: {
         main: "#1e1e1e",
      },
      background: {
         default: "#343e46",
         paper: "#485860",
      },
      text: {
         primary: "#F4F4F9",
      },
   },
   typography: {
      fontFamily: ["Jost Variable", "sans-serif"].join(","),
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
            <CssBaseline enableColorScheme />
            <Layout>
               <Component {...pageProps} />
            </Layout>
         </ThemeProvider>
      </>
   );
}
